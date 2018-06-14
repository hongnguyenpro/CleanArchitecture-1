package com.hieupham.data.source;

import com.hieupham.data.model.Mapable;
import io.reactivex.functions.Function;
import javax.inject.Inject;

/**
 * Created by hieupham on 6/14/18.
 */

public class Mapper {

    @Inject
    public Mapper() {
    }

    public <T extends Mapable<R>, R> Function<T, R> map() {
        return Mapable::map;
    }
}
