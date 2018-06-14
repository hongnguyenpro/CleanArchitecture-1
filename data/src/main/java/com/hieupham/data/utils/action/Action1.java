package com.hieupham.data.utils.action;

/**
 * Created by hieupham on 6/13/18.
 */

public interface Action1<T> extends Action {

    void call(T t);
}
