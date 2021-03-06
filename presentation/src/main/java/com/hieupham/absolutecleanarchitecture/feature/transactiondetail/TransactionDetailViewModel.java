package com.hieupham.absolutecleanarchitecture.feature.transactiondetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.utils.livedata.LiveDataObserver;
import com.hieupham.absolutecleanarchitecture.utils.livedata.Resource;
import com.hieupham.domain.entity.CompositeTransaction;
import com.hieupham.domain.interactor.usecase.GetTransactionUseCase;

/**
 * Created by hieupham on 5/14/18.
 */

class TransactionDetailViewModel extends ViewModel {

    private MutableLiveData<Resource<CompositeTransactionModelView>> liveTransactionDetail =
            new MutableLiveData<>();

    TransactionDetailViewModel(GetTransactionUseCase getTransactionUseCase) {
        super(getTransactionUseCase);
    }

    @Override
    void getTransactionDetail(String id) {
        GetTransactionUseCase.Input input = GetTransactionUseCase.Input.from(id);
        LiveDataObserver<CompositeTransaction, CompositeTransactionModelView> output =
                LiveDataObserver.from(liveTransactionDetail,
                        data -> CompositeTransactionModelView.instance().map(data));
        getTransactionUseCase.execute(input, output);
    }

    @Override
    LiveData<Resource<CompositeTransactionModelView>> liveTransactionDetail() {
        return liveTransactionDetail;
    }
}
