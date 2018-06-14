package com.hieupham.absolutecleanarchitecture.feature.transactionlist;

import android.arch.lifecycle.LiveData;
import com.hieupham.absolutecleanarchitecture.feature.BaseViewModel;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.utils.common.IntervalScheduler;
import com.hieupham.absolutecleanarchitecture.utils.livedata.Resource;
import com.hieupham.domain.interactor.usecase.GetLatestTransactionsUseCase;
import com.hieupham.domain.interactor.usecase.GetTransactionsUseCase;
import java.util.List;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class ViewModel extends BaseViewModel
        implements IntervalScheduler.SchedulerListener {

    protected GetTransactionsUseCase getTransactionsUseCase;
    protected GetLatestTransactionsUseCase getLatestTransactionsUseCase;

    ViewModel(GetTransactionsUseCase getTransactionsUseCase,
            GetLatestTransactionsUseCase getLatestTransactionsUseCase) {
        this.getTransactionsUseCase = getTransactionsUseCase;
        this.getLatestTransactionsUseCase = getLatestTransactionsUseCase;
    }

    abstract LiveData<Resource<List<CompositeTransactionModelView>>> nextTransactions();

    abstract LiveData<Resource<List<CompositeTransactionModelView>>> latestTransactions();

    abstract LiveData<Resource<List<CompositeTransactionModelView>>> refreshedTransactions();

    abstract void getNextTransactions();

    abstract void refreshTransactions();

    abstract void fetchLatestTransactions();

    @Override
    public void onDestroy() {
        super.onDestroy();
        getTransactionsUseCase.dispose();
        getLatestTransactionsUseCase.dispose();
    }
}
