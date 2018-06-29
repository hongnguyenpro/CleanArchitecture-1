package com.hieupham.data.source.local;

import com.hieupham.data.model.BlockData;
import com.hieupham.data.model.TransactionData;
import com.hieupham.data.source.local.api.DatabaseApi;
import com.hieupham.data.source.local.api.SharedPrefApi;
import com.hieupham.data.source.remote.api.response.TransactionResponse;
import com.hieupham.data.source.remote.api.response.TransactionsResponse;
import com.hieupham.data.utils.common.CommonUtils;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TransactionLocalDataSource extends LocalDataSource {

    @Inject
    TransactionLocalDataSource(DatabaseApi databaseApi, SharedPrefApi sharedPrefApi) {
        super(databaseApi, sharedPrefApi);
    }

    public Maybe<TransactionsResponse> getTransactions(final long blockNumber, final int limit) {
        return singleDb(roomApi -> roomApi.transactionDao().getMaxBlockNumber()).flatMapMaybe(
                maxBlockNumber -> {
                    if (blockNumber > maxBlockNumber) return Maybe.empty();
                    return maybeDb(roomApi -> roomApi.transactionDao().get(blockNumber, limit));
                }).flatMap(transactions -> {
            final TransactionsResponse response = new TransactionsResponse();
            response.setTransactions(transactions);
            if (transactions.isEmpty()) return Maybe.just(response);
            return maybeDb(
                    roomApi -> roomApi.assetDao().get(getAssetIds(transactions)).map(assets -> {
                        response.setAssets(assets);
                        return response;
                    }));
        }).flatMap(response -> {
            if (response.getTransactions().isEmpty()) return Maybe.just(response);
            return maybeDb(roomApi -> roomApi.blockDao().get(blockNumber).map(block -> {
                response.setBlocks(new ArrayList<BlockData>() {{
                    add(block);
                }});
                return response;
            }));
        });
    }

    public Maybe<TransactionResponse> getTransaction(final String id) {
        return maybeDb(roomApi -> roomApi.transactionDao().get(id)).flatMap(transaction -> {
            final TransactionResponse response = new TransactionResponse();
            response.setTransaction(transaction);
            return maybeDb(
                    roomApi -> roomApi.assetDao().get(transaction.getAssetId()).map(asset -> {
                        response.setAsset(asset);
                        return response;
                    }));
        })
                .flatMap(response -> maybeDb(roomApi -> roomApi.blockDao()
                        .get(response.getTransaction().getBlockNumber())
                        .map(block -> {
                            response.setBlock(block);
                            return response;
                        })));
    }

    public Completable save(final TransactionsResponse response) {
        return completableDb(roomApi -> {
            roomApi.blockDao().save(response.getBlocks());
            roomApi.assetDao().save(response.getAssets());
            roomApi.transactionDao().save(response.getTransactions());
            return Completable.complete();
        }).onErrorComplete();
    }

    public Completable save(final TransactionResponse response) {
        return completableDb(roomApi -> {
            roomApi.blockDao().save(response.getBlock());
            roomApi.assetDao().save(response.getAsset());
            roomApi.transactionDao().save(response.getTransaction());
            return Completable.complete();
        });
    }

    public Completable saveLastKnownBlockHeight(final long height) {
        return completableSharedPref(
                sharedPrefApi -> sharedPrefApi.put(SharedPrefApi.LAST_KNOWN_BLOCK_HEIGHT, height));
    }

    public Single<Long> getLastKnownBlockHeight() {
        return singleSharedPref((emitter, sharedPrefApi) -> emitter.onSuccess(
                sharedPrefApi.get(SharedPrefApi.LAST_KNOWN_BLOCK_HEIGHT, Long.class)));
    }

    private String[] getAssetIds(List<TransactionData> transactions) {
        List<String> assetIds = new ArrayList<>();
        for (TransactionData transaction : transactions) {
            if (assetIds.contains(transaction.getAssetId())) continue;
            assetIds.add(transaction.getAssetId());
        }
        return CommonUtils.toStringArray(assetIds);
    }
}
