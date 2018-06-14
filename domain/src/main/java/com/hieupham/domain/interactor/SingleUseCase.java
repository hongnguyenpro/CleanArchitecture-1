package com.hieupham.domain.interactor;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Created by hieupham on 6/14/18.
 */

public abstract class SingleUseCase<I extends UseCase.Input, O> extends UseCase<I, Single<O>> {

    public void execute(@NonNull I input, @NonNull Observer<O> output) {
        subscribe(buildDataStream(input).doOnSubscribe(output.onSubscribe())
                .subscribe(output.onSuccess(), output.onError()));
    }
}
