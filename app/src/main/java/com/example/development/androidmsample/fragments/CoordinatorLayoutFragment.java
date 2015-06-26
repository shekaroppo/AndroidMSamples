package com.example.development.androidmsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.development.androidmsample.R;
import com.example.development.androidmsample.adapter.SampleAdapter;
import com.example.development.androidmsample.adapter.ViewPagerAdapter;

import java.util.Arrays;

import butterknife.InjectView;

public class CoordinatorLayoutFragment extends BaseFragment {

    public static CoordinatorLayoutFragment newInstance() {
        return new CoordinatorLayoutFragment();
    }

    @InjectView(R.id.viewpager)
    ViewPager mViewPager;

    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @InjectView(R.id.tabs)
    TabLayout mTabLayout;


    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.coordinator_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView=view;
        setupToolbar(getString(R.string.coordinatorLayout));
        setupRecyclerView();
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_scroll).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        switch (item.getItemId()) {
            case R.id.menu_scroll:
                setupRecyclerView();
                Snackbar.make(mRootView, "You Selected Toolbar Scroll", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_scroll_enteralways:
                setupRecyclerView();
                Snackbar.make(mRootView, "You Selected Toolbar EnterAlways", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_tab_pin_scroll:
                setupTabLayout();
                Snackbar.make(mRootView, "You Selected TabLayout EnterAlways", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_tab_unpin_scroll:
                setupTabLayout();
                Snackbar.make(mRootView, "You Selected TabLayout Scroll", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                getActivity().supportInvalidateOptionsMenu();
                mTabLayout.invalidate();
                return true;
            case R.id.menu_snakbar_fab_anim:
                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_extended_toolbar_scroll:
                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_extended_toolbar_scroll_enteralways:
                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_extended_toolbar_scroll_enteralways_enteralwasyscollapsed:
                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hideTabLayout() {
        mTabLayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
    }

    private void hideRecyclerView() {
        mRecyclerView.setVisibility(View.GONE);
    }

    private void setupRecyclerView() {
        if(mRecyclerView.getVisibility()!=View.VISIBLE){
            mRecyclerView.setVisibility(View.VISIBLE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
            mRecyclerView.setAdapter(new SampleAdapter(getActivity(),
                    Arrays.asList(getResources().getStringArray(R.array.superheros))));
            hideTabLayout();
        }
    }

    private void setupTabLayout() {
        if(mTabLayout.getVisibility()!=View.VISIBLE){
            mTabLayout.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));
            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            setupViewPager();
            mTabLayout.setOnTabSelectedListener(tabSelectedListener);
            hideRecyclerView();
        }
    }

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

    private void setupViewPager() {
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }
}
