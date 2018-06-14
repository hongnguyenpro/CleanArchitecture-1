package com.hieupham.data.source.local.api;

import com.hieupham.data.source.local.api.dao.AssetDao;
import com.hieupham.data.source.local.api.dao.BlockDao;
import com.hieupham.data.source.local.api.dao.TransactionDao;
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
import io.reactivex.functions.Function;

/**
 * Created by hieupham on 5/14/18.
 */

public interface DatabaseApi {

    TransactionDao transactionDao();

    AssetDao assetDao();

    BlockDao blockDao();

    <T> Maybe<T> maybe(Function<DatabaseApi, Maybe<T>> function);

    <T> Flowable<T> flowable(Function<DatabaseApi, Flowable<T>> function);

    <T> Single<T> single(Function<DatabaseApi, Single<T>> function);

    <T> Observable<T> observable(Function<DatabaseApi, Observable<T>> function);

    Completable completable(Function<DatabaseApi, Completable> function);

    <T> Maybe<T> maybe(Action2<MaybeEmitter<? super T>, DatabaseApi> action);

    <T> Flowable<T> flowable(Action2<FlowableEmitter<? super T>, DatabaseApi> action);

    <T> Single<T> single(Action2<SingleEmitter<? super T>, DatabaseApi> action);

    <T> Observable<T> observable(Action2<ObservableEmitter<? super T>, DatabaseApi> action);
}
