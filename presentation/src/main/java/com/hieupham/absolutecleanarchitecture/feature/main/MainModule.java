package com.hieupham.absolutecleanarchitecture.feature.main;

import com.hieupham.absolutecleanarchitecture.di.ActivityScope;
import com.hieupham.absolutecleanarchitecture.di.FragmentBuilderModule;
import com.hieupham.absolutecleanarchitecture.feature.Navigator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/20/18.
 */

@Module(includes = FragmentBuilderModule.class)
public class MainModule {

    @Provides
    @ActivityScope
    ViewModel provideViewModel() {
        return new MainViewModel();
    }

    @Provides
    @ActivityScope
    Navigator<MainActivity> provideNavigator(MainActivity activity) {
        return new Navigator<>(activity);
    }
}
