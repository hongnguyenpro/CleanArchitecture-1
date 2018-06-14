package com.hieupham.data.source;

/**
 * Created by hieupham on 6/14/18.
 */

public abstract class Repository {

    protected Mapper mapper;

    Repository(Mapper mapper) {
        this.mapper = mapper;
    }
}
