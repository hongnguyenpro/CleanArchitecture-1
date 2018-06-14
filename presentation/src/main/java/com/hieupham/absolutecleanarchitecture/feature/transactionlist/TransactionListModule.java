package com.hieupham.absolutecleanarchitecture.feature.transactionlist;

import com.hieupham.absolutecleanarchitecture.di.FragmentScope;
import com.hieupham.absolutecleanarchitecture.feature.DialogManager;
import com.hieupham.absolutecleanarchitecture.feature.Navigator;
import com.hieupham.absolutecleanarchitecture.utils.common.IntervalScheduler;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public class TransactionListModule {

    @Provides
    @FragmentScope
    ViewModel provideViewModel(UseCase useCase, IntervalScheduler intervalScheduler) {
        return new TransactionListViewModel(useCase, intervalScheduler);
    }

    @Provides
    @FragmentScope
    UseCase provideUseCase(Transformer transformer, TransactionRepository bitmarkRepo) {
        return new TransactionListUseCase(transformer, bitmarkRepo);
    }

    @Provides
    @FragmentScope
    Navigator<TransactionListFragment> provideNavigator(TransactionListFragment fragment) {
        return new Navigator<>(fragment);
    }

    @Provides
    @FragmentScope
    TransactionListAdapter provideAdapter() {
        return new TransactionListAdapter();
    }

    @Provides
    @FragmentScope
    DialogManager provideDialogManager(TransactionListFragment fragment) {
        return new DialogManager(fragment.getActivity());
    }
}
