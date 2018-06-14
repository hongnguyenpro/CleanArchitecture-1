package com.hieupham.domain.interactor;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by hieupham on 6/14/18.
 */

public abstract class Observer<T> {

    private final Consumer<T> ON_SUCCESS = this::onSuccess;

    private final Consumer<? super Throwable> ON_ERROR = this::onError;

    private final Consumer<?> ON_SUBSCRIBE = o -> onSubscribed();

    private final Action ON_COMPLETE = this::onCompleted;

    Consumer<T> onSuccess() {
        return ON_SUCCESS;
    }

    Consumer<? super Throwable> onError() {
        return ON_ERROR;
    }

    Action onComplete() {
        return ON_COMPLETE;
    }

    <R> Consumer<R> onSubscribe() {
        return (Consumer<R>) ON_SUBSCRIBE;
    }

    public void onSuccess(T data) {

    }

    public void onError(Throwable throwable) {
    }

    public void onCompleted() {
    }

    public void onSubscribed() {
    }
}
