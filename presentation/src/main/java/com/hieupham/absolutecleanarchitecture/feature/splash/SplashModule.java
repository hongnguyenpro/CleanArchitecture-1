package com.hieupham.absolutecleanarchitecture.feature.splash;

import com.hieupham.absolutecleanarchitecture.di.ActivityScope;
import com.hieupham.absolutecleanarchitecture.feature.Navigator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 6/5/18.
 */

@Module
public class SplashModule {

    @ActivityScope
    @Provides
    Navigator<SplashActivity> provideNavigator(SplashActivity activity) {
        return new Navigator<>(activity);
    }
}
