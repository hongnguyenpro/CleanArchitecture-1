package com.hieupham.absolutecleanarchitecture.di;

import android.app.Activity;
import com.hieupham.absolutecleanarchitecture.feature.main.MainActivity;
import com.hieupham.absolutecleanarchitecture.feature.main.MainModule;
import com.hieupham.absolutecleanarchitecture.feature.splash.SplashActivity;
import com.hieupham.absolutecleanarchitecture.feature.splash.SplashModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by hieupham on 5/14/18.
 */

/**
 * The {@link Module} class declares how to inject an {@link Activity} instance into corresponding
 * {@link Module}
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    @ActivityScope
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = SplashModule.class)
    @ActivityScope
    abstract SplashActivity bindSplashActivity();
}
