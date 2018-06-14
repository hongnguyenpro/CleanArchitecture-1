package com.hieupham.domain.interactor.usecase;

import com.hieupham.domain.entity.CompositeTransaction;
import com.hieupham.domain.interactor.MaybeUseCase;
import com.hieupham.domain.interactor.UseCase;
import com.hieupham.domain.repository.TransactionRepository;
import io.reactivex.Maybe;

/**
 * Created by hieupham on 6/13/18.
 */

public class GetTransactionUseCase
        extends MaybeUseCase<GetTransactionUseCase.Input, CompositeTransaction> {

    private TransactionRepository transactionRepo;

    public GetTransactionUseCase(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    protected Maybe<CompositeTransaction> buildDataStream(Input input) {
        return transactionRepo.getTransaction(input.id);
    }

    public static class Input extends UseCase.Input {

        String id;

        private Input(String id) {
            this.id = id;
        }

        public static Input from(String id) {
            return new Input(id);
        }
    }
}
