package com.hieupham.domain.interactor;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Created by hieupham on 6/13/18.
 */

public abstract class ObservableUseCase<I extends UseCase.Input, O>
        extends UseCase<I, Observable<O>> {

    public void execute(@NonNull I input, @NonNull Observer<O> output) {
        subscribe(buildDataStream(input).doOnSubscribe(output.onSubscribe())
                .subscribe(output.onSuccess(), output.onError(), output.onComplete()));
    }
}
