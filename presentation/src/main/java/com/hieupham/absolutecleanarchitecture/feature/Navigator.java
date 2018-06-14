package com.hieupham.absolutecleanarchitecture.feature;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.hieupham.absolutecleanarchitecture.R;

/**
 * Created by hieupham on 5/14/18.
 */

public class Navigator<T> {

    public static final int BOTTOM_UP = 0x01;
    public static final int RIGHT_LEFT = 0x02;
    @Nullable
    private Fragment fragment;
    private FragmentActivity activity;
    private int anim;

    public Navigator(@NonNull T host) {
        if (host instanceof FragmentActivity) {
            this.activity = (FragmentActivity) host;
        } else if (host instanceof Fragment) {
            this.fragment = (Fragment) host;
            this.activity = ((Fragment) host).getActivity();
        }
    }

    public Navigator anim(int anim) {
        this.anim = anim;
        return this;
    }

    public void replaceFragment(@IdRes int container, Fragment fragment, boolean addToBackStack) {
        if (activity.isFinishing()) return;
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transactionAnim(transaction);
        transaction.replace(container, fragment);
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public void popFragment() {
        FragmentManager fragManager = activity.getSupportFragmentManager();
        fragManager.popBackStackImmediate();
    }

    public void addFragment(@IdRes int container, Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment)
                .commitAllowingStateLoss();
    }

    public void startActivity(Class clazz) {
        activity.startActivity(new Intent(activity, clazz));
        transactionAnim(activity);
    }

    public void finishActivity() {
        activity.finish();
    }

    private void transactionAnim(Activity activity) {
        switch (anim) {
            case BOTTOM_UP:
                activity.overridePendingTransition(R.anim.slide_bottom_in, 0);
                break;
            case RIGHT_LEFT:
                activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            default:
                break;
        }
    }

    private void transactionAnim(FragmentTransaction transaction) {
        switch (anim) {
            case BOTTOM_UP:
                transaction.setCustomAnimations(R.anim.slide_bottom_in, 0);
                break;
            case RIGHT_LEFT:
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out,
                        R.anim.slide_left_in, R.anim.slide_right_out);
                break;
            default:
                break;
        }
    }
}
