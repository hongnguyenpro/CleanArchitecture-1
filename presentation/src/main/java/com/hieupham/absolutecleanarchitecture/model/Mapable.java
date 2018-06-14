package com.hieupham.absolutecleanarchitecture.model;

/**
 * Created by hieupham on 6/14/18.
 */

public interface Mapable<T, R> {

    R map(T data);
}
