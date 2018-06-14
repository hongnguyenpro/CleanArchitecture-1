package com.hieupham.data;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hieupham.data.source.remote.api.middleware.BitmarkApiInterceptor;
import com.hieupham.data.source.remote.api.service.BitmarkApi;
import com.hieupham.data.source.remote.api.service.ServiceGenerator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class NetworkModule {

    private static final String API_ENDPOINT = "https://api.test.bitmark.com/v1/";

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Singleton
    @Provides
    BitmarkApiInterceptor provideAuthInterceptor(Context context) {
        return new BitmarkApiInterceptor(context);
    }

    @Singleton
    @Provides
    BitmarkApi provideBitmarkApi(Gson gson, BitmarkApiInterceptor interceptor) {
        return ServiceGenerator.createService(API_ENDPOINT, BitmarkApi.class, gson, interceptor);
    }
}
