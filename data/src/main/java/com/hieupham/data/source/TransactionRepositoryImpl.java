package com.hieupham.data.source;

import com.hieupham.data.source.local.TransactionLocalDataSource;
import com.hieupham.data.source.remote.TransactionRemoteDataSource;
import com.hieupham.data.source.remote.api.response.TransactionsResponse;
import com.hieupham.data.utils.common.CommonUtils;
import com.hieupham.domain.entity.CompositeTransaction;
import com.hieupham.domain.entity.CompositeTransactions;
import com.hieupham.domain.repository.TransactionRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static com.hieupham.data.utils.common.Constant.LIMITED_RESULT;

/**
 * Created by hieupham on 5/14/18.
 */

public class TransactionRepositoryImpl extends Repository implements TransactionRepository {
    private TransactionLocalDataSource localDataSource;
    private TransactionRemoteDataSource remoteDataSource;

    public TransactionRepositoryImpl(TransactionRemoteDataSource remoteDataSource,
            TransactionLocalDataSource localDataSource, Mapper mapper) {
        super(mapper);
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Maybe<CompositeTransactions> getTransactions(final long blockNumber) {
        return remoteDataSource.getTransactions(blockNumber, LIMITED_RESULT)
                .flatMapMaybe(
                        response -> localDataSource.save(response).andThen(Maybe.just(response)))
                .onErrorResumeNext(throwable -> {
                    if (CommonUtils.isNetworkError(throwable)) {
                        return localDataSource.getTransactions(blockNumber, LIMITED_RESULT);
                    }
                    return Maybe.error(throwable);
                })
                .map(TransactionsResponse::map);
    }

    @Override
    public Maybe<CompositeTransaction> getTransaction(final String id) {
        return remoteDataSource.getTransaction(id)
                .flatMapMaybe(
                        response -> localDataSource.save(response).andThen(Maybe.just(response)))
                .onErrorResumeNext(throwable -> {
                    if (CommonUtils.isNetworkError(throwable)) {
                        return localDataSource.getTransaction(id);
                    }
                    return Maybe.error(throwable);
                })
                .map(mapper.map());
    }

    @Override
    public Single<Long> getBlockHeight() {
        return remoteDataSource.getBlockHeight()
                .flatMap(height -> localDataSource.saveLastKnownBlockHeight(height)
                        .andThen(Single.just(height)))
                .onErrorResumeNext(throwable -> localDataSource.getLastKnownBlockHeight());
    }
}
