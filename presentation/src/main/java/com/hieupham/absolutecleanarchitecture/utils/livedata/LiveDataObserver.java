package com.hieupham.absolutecleanarchitecture.utils.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Looper;
import com.hieupham.absolutecleanarchitecture.utils.common.Function;
import com.hieupham.domain.interactor.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by hieupham on 6/14/18.
 */

public class LiveDataObserver<T, R> extends Observer<T> {

    @NonNull
    private MutableLiveData<Resource<R>> liveData = new MutableLiveData<>();

    private Function<T, R> mapFunction;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    private LiveDataObserver() {
    }

    public static <T, R> LiveDataObserver<T, R> from(MutableLiveData<Resource<R>> liveData,
            Function<T, R> mapFunction) {
        LiveDataObserver<T, R> observer = new LiveDataObserver<>();
        observer.liveData = liveData;
        observer.mapFunction = mapFunction;
        return observer;
    }

    @Override
    public void onSuccess(T data) {
        super.onSuccess(data);
        switchOnMain(() -> liveData.setValue(Resource.success(mapFunction.apply(data))));
    }

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);
        switchOnMain(() -> liveData.setValue(Resource.error(throwable, null)));
    }

    @Override
    public void onSubscribed() {
        super.onSubscribed();
        switchOnMain(() -> liveData.setValue(Resource.loading(null)));
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        switchOnMain(() -> liveData.setValue(Resource.success(null)));
    }

    private void switchOnMain(Runnable action) {
        mainHandler.post(action);
    }
}
