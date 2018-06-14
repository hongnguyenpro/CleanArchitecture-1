package com.hieupham.absolutecleanarchitecture.feature.transactionlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.model.mapper.CompositeTransactionModelViewMapper;
import com.hieupham.absolutecleanarchitecture.utils.common.Duration;
import com.hieupham.absolutecleanarchitecture.utils.common.IntervalScheduler;
import com.hieupham.absolutecleanarchitecture.utils.livedata.LiveDataObserver;
import com.hieupham.absolutecleanarchitecture.utils.livedata.Resource;
import com.hieupham.domain.entity.CompositeTransactions;
import com.hieupham.domain.interactor.UseCase;
import com.hieupham.domain.interactor.usecase.GetLatestTransactionsUseCase;
import com.hieupham.domain.interactor.usecase.GetTransactionsUseCase;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hieupham on 5/14/18.
 */

public class TransactionListViewModel extends ViewModel {

    private MutableLiveData<Resource<List<CompositeTransactionModelView>>> liveNextTransactions =
            new MutableLiveData<>();
    private MutableLiveData<Resource<List<CompositeTransactionModelView>>> liveLatestTransactions =
            new MutableLiveData<>();
    private MutableLiveData<Resource<List<CompositeTransactionModelView>>> liveRefreshTransactions =
            new MutableLiveData<>();
    private CompositeTransactionModelViewMapper mapper;
    private IntervalScheduler taskScheduler;
    private Long blockNumber;

    TransactionListViewModel(GetTransactionsUseCase getTransactionsUseCase,
            GetLatestTransactionsUseCase getLatestTransactionsUseCase,
            IntervalScheduler taskScheduler, CompositeTransactionModelViewMapper mapper) {
        super(getTransactionsUseCase, getLatestTransactionsUseCase);
        this.taskScheduler = taskScheduler;
        this.taskScheduler.observe(this);
        this.mapper = mapper;
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
        return liveNextTransactions;
    }

    @Override
    LiveData<Resource<List<CompositeTransactionModelView>>> latestTransactions() {
        return liveLatestTransactions;
    }

    @Override
    LiveData<Resource<List<CompositeTransactionModelView>>> refreshedTransactions() {
        return liveRefreshTransactions;
    }

    @Override
    void getNextTransactions() {
        if (blockNumber == null || blockNumber == 0) {
            fetchLatestTransactions();
        } else {
            LiveDataObserver<CompositeTransactions, List<CompositeTransactionModelView>> output =
                    LiveDataObserver.from(liveNextTransactions, mapper::transform);
            GetTransactionsUseCase.Input input = GetTransactionsUseCase.Input.from(--blockNumber);
            getTransactionsUseCase.execute(input, output);
        }
    }

    @Override
    void refreshTransactions() {
        blockNumber = null;
        fetchLatestTransactions();
    }

    @Override
    void fetchLatestTransactions() {
        LiveDataObserver<CompositeTransactions, List<CompositeTransactionModelView>> output =
                LiveDataObserver.from(liveLatestTransactions, mapper::transform);
        getLatestTransactionsUseCase.execute(UseCase.EmptyInput.instance(), output);
    }

    @Override
    public void onSchedule() {
        fetchLatestTransactions();
    }
}
