package com.hieupham.absolutecleanarchitecture.feature;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;
import com.hieupham.absolutecleanarchitecture.di.DaggerAppCompatActivity;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class BaseAppCompatActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewModel() != null) {
            getLifecycle().addObserver(viewModel());
        }
        setContentView(layoutRes());
        ButterKnife.bind(this);
        initComponents();
        observe();
    }

    /**
     * Define the layout res id can be used to {@link Activity#setContentView(int)}
     *
     * @return the layout res id
     */
    @LayoutRes
    protected abstract int layoutRes();

    /**
     * Define the {@link BaseViewModel} instance
     *
     * @return the {@link BaseViewModel} instance
     */
    @Nullable
    protected abstract BaseViewModel viewModel();

    /**
     * Init {@link View} components here. Such as set adapter for {@link RecyclerView}, set listener
     * or anything else
     */
    protected void initComponents() {
    }

    /**
     * Observe data change from ViewModel
     */
    protected void observe() {
    }
}
