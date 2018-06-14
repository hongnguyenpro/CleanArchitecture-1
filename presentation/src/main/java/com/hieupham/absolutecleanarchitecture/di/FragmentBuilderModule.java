package com.hieupham.absolutecleanarchitecture.di;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.hieupham.absolutecleanarchitecture.feature.transactiondetail.TransactionDetailFragment;
import com.hieupham.absolutecleanarchitecture.feature.transactiondetail.TransactionDetailModule;
import com.hieupham.absolutecleanarchitecture.feature.transactionlist.TransactionListFragment;
import com.hieupham.absolutecleanarchitecture.feature.transactionlist.TransactionListModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by hieupham on 5/14/18.
 */

/**
 * The {@link Module} class declares how to inject a {@link Fragment} instance into corresponding
 * {@link Module} <br>
 * Because Dagger 2 is lazy initialization so you don't need to care about why we need to define
 * how
 * to bind all {@link Fragment} here. <br>
 * <b>NOTE:</b> You should include this {@link Module} in the {@link Activity} module that uses
 * {@link Fragment} to interact.
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = TransactionListModule.class)
    @FragmentScope
    abstract TransactionListFragment bindTransactionListFragment();

    @ContributesAndroidInjector(modules = TransactionDetailModule.class)
    @FragmentScope
    abstract TransactionDetailFragment bindTransactionDetailFragment();
}
