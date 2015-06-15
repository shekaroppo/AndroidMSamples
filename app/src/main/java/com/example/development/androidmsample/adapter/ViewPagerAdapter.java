package com.example.development.androidmsample.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.development.androidmsample.fragments.SampleFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        SampleFragment tab;
        Bundle args = new Bundle();

        switch (position) {
            case 0:
                tab = new SampleFragment();
                args.putString("name","Tab1");
                tab.setArguments(args);
                return tab;
            case 1:
                tab = new SampleFragment();
                args.putString("name","Tab2");
                tab.setArguments(args);
                return tab;
            case 2:
                tab = new SampleFragment();
                args.putString("name","Tab3");
                tab.setArguments(args);
                return tab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}