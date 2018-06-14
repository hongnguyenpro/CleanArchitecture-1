package com.hieupham.absolutecleanarchitecture.feature.transactionlist;

import android.arch.lifecycle.LiveData;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.utils.common.Duration;
import com.hieupham.absolutecleanarchitecture.utils.common.IntervalScheduler;
import com.hieupham.absolutecleanarchitecture.utils.livedata.CompositeLiveData;
import com.hieupham.absolutecleanarchitecture.utils.livedata.Resource;
import com.hieupham.domain.interactor.usecase.GetTransactionsUseCase;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hieupham on 5/14/18.
 */

public class TransactionListViewModel extends ViewModel {

    private CompositeLiveData<Resource<List<CompositeTransactionModelView>>> liveNextTransactions =
            new CompositeLiveData<>();
    private CompositeLiveData<Resource<List<CompositeTransactionModelView>>>
            liveLatestTransactions = new CompositeLiveData<>();
    private CompositeLiveData<Resource<List<CompositeTransactionModelView>>>
            liveRefreshTransactions = new CompositeLiveData<>();
    private IntervalScheduler taskScheduler;
    private Long blockHeight;
    private Long blockNumber;


    TransactionListViewModel(GetTransactionsUseCase getTransactionsUseCase,
            IntervalScheduler taskScheduler) {
        super(getTransactionsUseCase);
        this.taskScheduler = taskScheduler;
        this.taskScheduler.observe(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Duration duration = Duration.from(2, TimeUnit.MINUTES);
        taskScheduler.triggerOnMain().delay(duration).period(duration).schedule();
    }

    @Override
    public void onDestroy() {
        taskScheduler.cancel();
        super.onDestroy();
    }

    @Override
    LiveData<Resource<List<CompositeTransactionModelView>>> nextTransactions() {
        return liveNextTransactions.asLiveData();
    }

    @Override
    LiveData<Resource<List<CompositeTransactionModelView>>> latestTransactions() {
        return liveLatestTransactions.asLiveData();
    }

    @Override
    LiveData<Resource<List<CompositeTransactionModelView>>> refreshedTransactions() {
        return liveRefreshTransactions.asLiveData();
    }

    @Override
    void getNextTransactions() {
        liveNextTransactions.addSource(liveGetTransactions());
    }

    @Override
    void refreshTransactions() {
        liveRefreshTransactions.addAtomicSource(liveRefreshTransactions());
    }

    @Override
    void fetchLatestTransactions() {
        liveLatestTransactions.addAtomicSource(liveLatestTransactions());
    }

    @Override
    public void onSchedule() {
        fetchLatestTransactions();
    }

    private LiveData<Resource<List<CompositeTransactionModelView>>> liveGetTransactions() {
        return getTransactionsUseCase.getNextTransactions();
    }

    private LiveData<Resource<List<CompositeTransactionModelView>>> liveLatestTransactions() {
        return getTransactionsUseCase.fetchLatestTransactions();
    }

    private LiveData<Resource<List<CompositeTransactionModelView>>> liveRefreshTransactions() {
        blockNumber = null;
        blockHeight = null;
        GetTransactionsUseCase.Input input = GetTransactionsUseCase.Input.from(blockNumber);
        return getTransactionsUseCase.refreshTransactions();
    }
}
