package com.hieupham.absolutecleanarchitecture.feature.transactiondetail;

import android.arch.lifecycle.LiveData;
import com.hieupham.absolutecleanarchitecture.feature.BaseViewModel;
import com.hieupham.absolutecleanarchitecture.model.CompositeTransactionModelView;
import com.hieupham.absolutecleanarchitecture.utils.livedata.Resource;
import com.hieupham.domain.interactor.usecase.GetTransactionUseCase;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class ViewModel extends BaseViewModel {

    protected GetTransactionUseCase getTransactionUseCase;

    ViewModel(GetTransactionUseCase getTransactionUseCase) {
        this.getTransactionUseCase = getTransactionUseCase;
    }

    abstract void getTransactionDetail(String id);

    abstract LiveData<Resource<CompositeTransactionModelView>> liveTransactionDetail();

    @Override
    public void onDestroy() {
        super.onDestroy();
        getTransactionUseCase.dispose();
    }
}
