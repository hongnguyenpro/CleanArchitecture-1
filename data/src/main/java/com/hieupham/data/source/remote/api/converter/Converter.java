package com.hieupham.data.source.remote.api.converter;

import com.hieupham.data.source.remote.api.response.InfoResponse;
import io.reactivex.functions.Function;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/15/18.
 */

public class Converter {

    @Inject
    public Converter() {
    }

    public Function<InfoResponse, Long> toInfo() {
        return InfoResponse::getHeight;
    }
}
