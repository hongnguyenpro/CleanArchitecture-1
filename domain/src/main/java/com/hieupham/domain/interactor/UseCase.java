package com.hieupham.domain.interactor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by hieupham on 6/14/18.
 */

public abstract class UseCase<I extends UseCase.Input, O> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected abstract O buildDataStream(I input);

    void subscribe(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        compositeDisposable.clear();
    }

    public static abstract class Input {
    }

    public static class EmptyInput extends Input {

        private EmptyInput(){}

        public static EmptyInput instance(){
            return new EmptyInput();
        }
    }
}
