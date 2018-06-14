package com.hieupham.domain.interactor.usecase;

import com.hieupham.domain.entity.CompositeTransactions;
import com.hieupham.domain.interactor.MaybeUseCase;
import com.hieupham.domain.interactor.UseCase;
import com.hieupham.domain.repository.TransactionRepository;
import io.reactivex.Maybe;
import javax.inject.Inject;

/**
 * Created by hieupham on 6/13/18.
 */

public class GetTransactionsUseCase
        extends MaybeUseCase<GetTransactionsUseCase.Input, CompositeTransactions> {

    private TransactionRepository transactionRepo;

    @Inject
    public GetTransactionsUseCase(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    protected Maybe<CompositeTransactions> buildDataStream(Input input) {
        return transactionRepo.getTransactions(input.blockNumber);
    }

    public static class Input extends UseCase.Input {

        long blockNumber;

        private Input(long blockNumber) {
            this.blockNumber = blockNumber;
        }

        public static Input from(long blockNumber) {
            return new Input(blockNumber);
        }
    }
}
