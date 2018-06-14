package com.hieupham.domain.interactor;

import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;

/**
 * Created by hieupham on 6/14/18.
 */

public abstract class MaybeUseCase<I extends UseCase.Input, O> extends UseCase<I, Maybe<O>> {

    public void execute(@NonNull I input, @NonNull Observer<O> output) {
        subscribe(buildDataStream(input).doOnSubscribe(output.onSubscribe())
                .subscribe(output.onSuccess(), output.onError(), output.onComplete()));
    }
}
