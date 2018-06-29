package com.hieupham.absolutecleanarchitecture;

import com.hieupham.absolutecleanarchitecture.di.DaggerApplication;

/**
 * Created by hieupham on 5/14/18.
 */

public class CleanArchitectureApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}
