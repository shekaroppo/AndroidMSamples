package com.example.development.androidmsample.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class Navigator{
    @NonNull
    protected final FragmentManager mFragmentManager;

    @IdRes
    protected final int mDefaultContainer;

    public Navigator(@NonNull final FragmentManager fragmentManager, @IdRes final int defaultContainer){
        mFragmentManager = fragmentManager;
        mDefaultContainer =defaultContainer;
    }

    public void setRootFragment(final Fragment startFragment){
        if(getSize() > 0){
            this.clearHistory();
        }
        this.replaceFragment(startFragment);
    }

    private void replaceFragment(final Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(mDefaultContainer, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public int getSize() {
        return mFragmentManager.getBackStackEntryCount();
    }


    public void clearHistory() {
        //noinspection StatementWithEmptyBody - it works as wanted
        while(mFragmentManager.popBackStackImmediate());
    }
}
