package com.hieupham.data.source.remote;

import com.hieupham.data.source.remote.api.converter.Converter;
import com.hieupham.data.source.remote.api.response.TransactionResponse;
import com.hieupham.data.source.remote.api.response.TransactionsResponse;
import com.hieupham.data.source.remote.api.service.BitmarkApi;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TransactionRemoteDataSource extends RemoteDataSource {

    @Inject
    public TransactionRemoteDataSource(BitmarkApi bitmarkApi, Converter converter) {
        super(bitmarkApi, converter);
    }

    public Single<TransactionsResponse> getTransactions(final long blockNumber, final int limit) {
        return bitmarkApi.getTransactions(blockNumber, "later", limit, true, true)
                .subscribeOn(Schedulers.io());
    }

    public Single<TransactionResponse> getTransaction(String id) {
        return bitmarkApi.getTransaction(id, true, true).subscribeOn(Schedulers.io());
    }

    public Single<Long> getBlockHeight() {
        return bitmarkApi.getInfo().map(converter.toInfo()).subscribeOn(Schedulers.io());
    }
}
