package com.hieupham.absolutecleanarchitecture.feature.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hieupham.absolutecleanarchitecture.R;
import com.hieupham.absolutecleanarchitecture.feature.BaseAppCompatActivity;
import com.hieupham.absolutecleanarchitecture.feature.BaseViewModel;
import com.hieupham.absolutecleanarchitecture.feature.Navigator;
import com.hieupham.absolutecleanarchitecture.feature.transactionlist.TransactionListFragment;
import javax.inject.Inject;

public class MainActivity extends BaseAppCompatActivity {

    @Inject
    ViewModel viewModel;

    @Inject
    Navigator<MainActivity> navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.replaceFragment(R.id.layout_root, TransactionListFragment.newInstance(), false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseViewModel viewModel() {
        return viewModel;
    }
}
