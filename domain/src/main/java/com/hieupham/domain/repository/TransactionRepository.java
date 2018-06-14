package com.hieupham.domain.repository;

import com.hieupham.domain.entity.CompositeTransaction;
import com.hieupham.domain.entity.CompositeTransactions;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by hieupham on 6/13/18.
 */

public interface TransactionRepository extends Repository {

    Maybe<CompositeTransactions> getTransactions(final long blockNumber);

    Maybe<CompositeTransaction> getTransaction(final String id);

    Single<Long> getBlockHeight();
}
