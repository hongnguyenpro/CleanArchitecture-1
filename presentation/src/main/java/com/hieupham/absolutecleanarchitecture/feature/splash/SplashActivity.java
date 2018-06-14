package com.hieupham.absolutecleanarchitecture.feature.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.hieupham.absolutecleanarchitecture.R;
import com.hieupham.absolutecleanarchitecture.feature.BaseAppCompatActivity;
import com.hieupham.absolutecleanarchitecture.feature.BaseViewModel;
import com.hieupham.absolutecleanarchitecture.feature.Navigator;
import com.hieupham.absolutecleanarchitecture.feature.main.MainActivity;
import javax.inject.Inject;

import static com.hieupham.absolutecleanarchitecture.feature.Navigator.RIGHT_LEFT;

/**
 * Created by hieupham on 6/5/18.
 */

public class SplashActivity extends BaseAppCompatActivity {

    @Inject
    Navigator<SplashActivity> navigator;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigator.anim(RIGHT_LEFT).startActivity(MainActivity.class);
                navigator.finishActivity();
            }
        }, 2000);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected BaseViewModel viewModel() {
        return null;
    }
}
