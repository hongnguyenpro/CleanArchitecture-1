package com.hieupham.absolutecleanarchitecture;

import android.app.Application;
import com.hieupham.absolutecleanarchitecture.di.ActivityBuilderModule;
import com.hieupham.data.NetworkModule;
import com.hieupham.data.RepositoryModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

/**
 * Created by hieupham on 5/14/18.
 */

@Component(modules = {
        AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilderModule.class,
        RepositoryModule.class, NetworkModule.class
})
@Singleton
public interface AppComponent extends AndroidInjector<CleanArchitectureApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
