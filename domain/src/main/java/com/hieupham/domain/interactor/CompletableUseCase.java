package com.hieupham.domain.interactor;

import io.reactivex.Completable;
import io.reactivex.annotations.NonNull;

/**
 * Created by hieupham on 6/14/18.
 */

public abstract class CompletableUseCase<I extends UseCase.Input> extends UseCase<I, Completable> {

    public void execute(@NonNull I input, @NonNull final Observer<?> output) {
        subscribe(buildDataStream(input).doOnSubscribe(output.onSubscribe())
                .subscribe(output.onComplete(), output.onError()));
    }
}
