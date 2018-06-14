package com.hieupham.absolutecleanarchitecture.feature.transactiondetail;

import com.hieupham.absolutecleanarchitecture.di.FragmentScope;
import com.hieupham.domain.interactor.usecase.GetTransactionUseCase;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public class TransactionDetailModule {

    @Provides
    @FragmentScope
    ViewModel provideViewModel(GetTransactionUseCase useCase) {
        return new TransactionDetailViewModel(useCase);
    }
}
