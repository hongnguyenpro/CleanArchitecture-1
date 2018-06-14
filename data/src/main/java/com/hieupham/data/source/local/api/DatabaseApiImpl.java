package com.hieupham.data.source.local.api;

import com.hieupham.data.source.local.api.dao.AssetDao;
import com.hieupham.data.source.local.api.dao.BlockDao;
import com.hieupham.data.source.local.api.dao.TransactionDao;
import com.hieupham.data.utils.action.Action2;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hieupham on 5/14/18.
 */

public class DatabaseApiImpl implements DatabaseApi {

    private DatabaseManager databaseManager;

    public DatabaseApiImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public TransactionDao transactionDao() {
        return databaseManager.transactionDao();
    }

    @Override
    public AssetDao assetDao() {
        return databaseManager.assetDao();
    }

    @Override
    public BlockDao blockDao() {
        return databaseManager.blockDao();
    }

    @Override
    public <T> Maybe<T> maybe(Function<DatabaseApi, Maybe<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Maybe.error(e);
        }
    }

    @Override
    public <T> Flowable<T> flowable(Function<DatabaseApi, Flowable<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Flowable.error(e);
        }
    }

    @Override
    public <T> Single<T> single(Function<DatabaseApi, Single<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Single.error(e);
        }
    }

    @Override
    public <T> Observable<T> observable(Function<DatabaseApi, Observable<T>> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Observable.error(e);
        }
    }

    @Override
    public Completable completable(Function<DatabaseApi, Completable> function) {
        try {
            return function.apply(this).subscribeOn(Schedulers.io());
        } catch (Exception e) {
            return Completable.error(e);
        }
    }

    @Override
    public <T> Maybe<T> maybe(final Action2<MaybeEmitter<? super T>, DatabaseApi> action) {
        return Maybe.create((MaybeOnSubscribe<T>) e -> {
            action.call(e, DatabaseApiImpl.this);
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Flowable<T> flowable(final Action2<FlowableEmitter<? super T>, DatabaseApi> action) {
        return Flowable.create((FlowableOnSubscribe<T>) e -> {
            action.call(e, DatabaseApiImpl.this);
            e.onComplete();
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Single<T> single(final Action2<SingleEmitter<? super T>, DatabaseApi> action) {
        return Single.create((SingleOnSubscribe<T>) e -> action.call(e, DatabaseApiImpl.this))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Observable<T> observable(
            final Action2<ObservableEmitter<? super T>, DatabaseApi> action) {
        return Observable.create((ObservableOnSubscribe<T>) e -> {
            action.call(e, DatabaseApiImpl.this);
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }
}
