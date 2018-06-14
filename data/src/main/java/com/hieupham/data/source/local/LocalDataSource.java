package com.hieupham.data.source.local;

import com.hieupham.data.source.local.api.DatabaseApi;
import com.hieupham.data.source.local.api.SharedPrefApi;
import com.hieupham.data.utils.action.Action1;
import com.hieupham.data.utils.action.Action2;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hieupham on 5/14/18.
 */

public abstract class LocalDataSource {

    private SharedPrefApi sharedPrefApi;
    private DatabaseApi roomApi;

    LocalDataSource(DatabaseApi roomApi, SharedPrefApi sharedPrefApi) {
        this.roomApi = roomApi;
        this.sharedPrefApi = sharedPrefApi;
    }

    protected <T> Maybe<T> maybeDb(Function<DatabaseApi, Maybe<T>> function) {
        return roomApi.maybe(function);
    }

    protected <T> Flowable<T> flowableDb(Function<DatabaseApi, Flowable<T>> function) {
        return roomApi.flowable(function);
    }

    protected <T> Single<T> singleDb(Function<DatabaseApi, Single<T>> function) {
        return roomApi.single(function);
    }

    protected <T> Observable<T> observableDb(Function<DatabaseApi, Observable<T>> function) {
        return roomApi.observable(function);
    }

    protected Completable completableDb(Function<DatabaseApi, Completable> function) {
        return roomApi.completable(function);
    }

    protected <T> Maybe<T> maybeDb(Action2<MaybeEmitter<? super T>, DatabaseApi> action) {
        return roomApi.maybe(action);
    }

    protected <T> Flowable<T> flowableDb(Action2<FlowableEmitter<? super T>, DatabaseApi> action) {
        return roomApi.flowable(action);
    }

    protected <T> Single<T> singleDb(Action2<SingleEmitter<? super T>, DatabaseApi> action) {
        return roomApi.single(action);
    }

    protected <T> Observable<T> observableDb(
            Action2<ObservableEmitter<? super T>, DatabaseApi> action) {
        return roomApi.observable(action);
    }

    protected Completable completableSharedPref(final Action1<SharedPrefApi> action) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                action.call(sharedPrefApi);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    protected <T> Single<T> singleSharedPref(
            final Action2<SingleEmitter<? super T>, SharedPrefApi> action) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> emitter) throws Exception {
                try {
                    action.call(emitter, sharedPrefApi);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
