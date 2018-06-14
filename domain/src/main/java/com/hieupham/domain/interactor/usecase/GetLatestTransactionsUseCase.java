package com.hieupham.domain.interactor.usecase;

import com.hieupham.domain.entity.CompositeTransactions;
import com.hieupham.domain.interactor.MaybeUseCase;
import com.hieupham.domain.interactor.UseCase;
import com.hieupham.domain.repository.TransactionRepository;
import io.reactivex.Maybe;
import java.util.Objects;

/**
 * Created by hieupham on 6/14/18.
 */

public class GetLatestTransactionsUseCase
        extends MaybeUseCase<UseCase.EmptyInput, CompositeTransactions> {

    private TransactionRepository transactionRepo;
    private Long blockHeight;

    public GetLatestTransactionsUseCase(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    protected Maybe<CompositeTransactions> buildDataStream(EmptyInput input) {
        return transactionRepo.getBlockHeight().flatMapMaybe(height -> {
            if (Objects.equals(blockHeight, height)) return Maybe.empty();
            blockHeight = height;
            return transactionRepo.getTransactions(height);
        });
    }
}
