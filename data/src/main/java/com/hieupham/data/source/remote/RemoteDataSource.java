package com.hieupham.data.source.remote;

import com.hieupham.data.source.remote.api.converter.Converter;
import com.hieupham.data.source.remote.api.service.BitmarkApi;

/**
 * Created by hieupham on 5/14/18.
 */

public abstract class RemoteDataSource {

    protected BitmarkApi bitmarkApi;
    protected Converter converter;

    RemoteDataSource(BitmarkApi bitmarkApi, Converter converter) {
        this.bitmarkApi = bitmarkApi;
        this.converter = converter;
    }
}
