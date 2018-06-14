package com.hieupham.domain.interactor.usecase;

import com.hieupham.domain.interactor.SingleUseCase;
import com.hieupham.domain.interactor.UseCase;
import com.hieupham.domain.repository.TransactionRepository;
import io.reactivex.Single;
import javax.inject.Inject;

/**
 * Created by hieupham on 6/14/18.
 */

public class GetBlockHeightUseCase extends SingleUseCase<UseCase.EmptyInput, Long> {

    private TransactionRepository transactionRepo;

    @Inject
    public GetBlockHeightUseCase(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    protected Single<Long> buildDataStream(EmptyInput input) {
        return transactionRepo.getBlockHeight();
    }
}
