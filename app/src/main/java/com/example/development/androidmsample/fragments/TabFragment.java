package com.example.development.androidmsample.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.development.androidmsample.MainActivity;
import com.example.development.androidmsample.R;
import com.example.development.androidmsample.adapter.ViewPagerAdapter;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Shekar on 6/14/15.
 */

public class TabFragment extends BaseFragment {

    private static final int AMOUNT_OF_DATA = 50;

    public static TabFragment newInstance() {
        return new TabFragment();
    }

    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @InjectView(R.id.tabRootLayout)
    View rootView;


    @InjectView(R.id.tabs)
    TabLayout mTabLayout;

    @InjectView(R.id.viewpager)
    ViewPager mViewPager;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    public TabFragment() {
    }

    @Override
    protected int getLayout() {
        return R.layout.tab_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupTabLayout();
    }

    @OnClick(R.id.fab)
    void onClick() {
        Snackbar.make(rootView, "This is Snackbar", Snackbar.LENGTH_LONG).show(); // Do not forget to show!
    }

    protected void setupToolbar() {
        mToolbar.setTitle(R.string.tabLayout);
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
    }

    private void setupTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupViewPager();
        mTabLayout.setOnTabSelectedListener(tabSelectedListener);
    }

    private void setupViewPager() {
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @NonNull
    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

}
