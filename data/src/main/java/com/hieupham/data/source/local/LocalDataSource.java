package com.hieupham.data.source.local;

import com.hieupham.data.source.local.api.DatabaseApi;
import com.hieupham.data.source.local.api.SharedPrefApi;
import com.hieupham.data.utils.action.Action1;
import com.hieupham.data.utils.action.Action2;
import io.reactivex.Completable;
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
    private DatabaseApi databaseApi;

    LocalDataSource(DatabaseApi databaseApi, SharedPrefApi sharedPrefApi) {
        this.databaseApi = databaseApi;
        this.sharedPrefApi = sharedPrefApi;
    }

    protected <T> Maybe<T> maybeDb(Function<DatabaseApi, Maybe<T>> function) {
        return databaseApi.maybe(function);
    }

    protected <T> Flowable<T> flowableDb(Function<DatabaseApi, Flowable<T>> function) {
        return databaseApi.flowable(function);
    }

    protected <T> Single<T> singleDb(Function<DatabaseApi, Single<T>> function) {
        return databaseApi.single(function);
    }

    protected <T> Observable<T> observableDb(Function<DatabaseApi, Observable<T>> function) {
        return databaseApi.observable(function);
    }

    protected Completable completableDb(Function<DatabaseApi, Completable> function) {
        return databaseApi.completable(function);
    }

    protected <T> Maybe<T> maybeDb(Action2<MaybeEmitter<? super T>, DatabaseApi> action) {
        return databaseApi.maybe(action);
    }

    protected <T> Flowable<T> flowableDb(Action2<FlowableEmitter<? super T>, DatabaseApi> action) {
        return databaseApi.flowable(action);
    }

    protected <T> Single<T> singleDb(Action2<SingleEmitter<? super T>, DatabaseApi> action) {
        return databaseApi.single(action);
    }

    protected <T> Observable<T> observableDb(
            Action2<ObservableEmitter<? super T>, DatabaseApi> action) {
        return databaseApi.observable(action);
    }

    protected Completable completableSharedPref(final Action1<SharedPrefApi> action) {
        return Completable.create(e -> {
            action.call(sharedPrefApi);
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }

    protected <T> Single<T> singleSharedPref(
            final Action2<SingleEmitter<? super T>, SharedPrefApi> action) {
        return Single.create((SingleOnSubscribe<T>) emitter -> {
            try {
                action.call(emitter, sharedPrefApi);
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io());
    }
}
