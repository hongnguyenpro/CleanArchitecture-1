package com.hieupham.domain.interactor.usecase;

import com.hieupham.domain.entity.CompositeTransactions;
import com.hieupham.domain.interactor.MaybeUseCase;
import com.hieupham.domain.interactor.UseCase;
import com.hieupham.domain.repository.TransactionRepository;
import io.reactivex.Maybe;
import java.util.Objects;
import javax.inject.Inject;

/**
 * Created by hieupham on 6/13/18.
 */

public class GetTransactionsUseCase
        extends MaybeUseCase<UseCase.EmptyInput, CompositeTransactions> {

    private TransactionRepository transactionRepo;
    private Long blockNumber;
    private Long blockHeight;

    @Inject
    public GetTransactionsUseCase(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public GetTransactionsUseCase next() {
        --blockNumber;
        return this;
    }

    public GetTransactionsUseCase refresh() {
        blockHeight = null;
        blockNumber = null;
        return this;
    }

    public GetTransactionsUseCase fetchLatest() {
        blockHeight = null;
        return this;
    }

    @Override
    protected Maybe<CompositeTransactions> buildDataStream(EmptyInput input) {
        return blockHeight == null ? fetchLatestTransactions()
                : blockNumber == null ? fetchLatestTransactions() : getTransactions();
    }

    private Maybe<CompositeTransactions> fetchLatestTransactions() {
        return transactionRepo.getBlockHeight().flatMapMaybe(height -> {
            if (blockNumber == null && height != 0) blockNumber = height;
            if (Objects.equals(blockHeight, height)) return Maybe.empty();
            blockHeight = height;
            return transactionRepo.getTransactions(height);
        });
    }

    private Maybe<CompositeTransactions> getTransactions() {
        return transactionRepo.getTransactions(blockNumber);
    }
}
