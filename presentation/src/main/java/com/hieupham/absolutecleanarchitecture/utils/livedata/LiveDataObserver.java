package com.hieupham.absolutecleanarchitecture.utils.livedata;

import android.arch.lifecycle.MutableLiveData;
import com.hieupham.absolutecleanarchitecture.utils.common.MapFunction;
import com.hieupham.domain.interactor.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by hieupham on 6/14/18.
 */

public class LiveDataObserver<T, R> extends Observer<T> {

    @NonNull
    private MutableLiveData<Resource<R>> liveData = new MutableLiveData<>();

    private MapFunction<T, R> mapFunction;

    private LiveDataObserver() {
    }

    public static <T, R> LiveDataObserver<T, R> from(MutableLiveData<Resource<R>> liveData, MapFunction<T, R> function) {
        LiveDataObserver<T, R> observer = new LiveDataObserver<>();
        observer.liveData = liveData;
        observer.mapFunction = function;
        return observer;
    }

    @Override
    public void onSuccess(T data) {
        super.onSuccess(data);
        liveData.setValue(Resource.success(mapFunction.apply(data)));
    }

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);
        liveData.setValue(Resource.error(throwable, null));
    }

    @Override
    public void onSubscribed() {
        super.onSubscribed();
        liveData.setValue(Resource.loading(null));
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        liveData.setValue(Resource.success(null));
    }
}
