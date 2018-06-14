package com.hieupham.domain.interactor;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;

/**
 * Created by hieupham on 6/14/18.
 */

public abstract class FlowableUseCase<I extends UseCase.Input, O> extends UseCase<I, Flowable<O>> {

    public void execute(@NonNull I input, @NonNull Observer<O> output) {
        subscribe(buildDataStream(input).doOnSubscribe(output.onSubscribe())
                .subscribe(output.onSuccess(), output.onError(), output.onComplete()));
    }
}
