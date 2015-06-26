package com.example.development.androidmsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.development.androidmsample.R;
import com.example.development.androidmsample.adapter.ViewPagerAdapter;

import butterknife.InjectView;

/**
 * Created by Shekar on 6/26/15.
 */
public class TabLayoutFragment extends BaseFragment {

    @InjectView(R.id.tabs)
    TabLayout mTabLayout;

    @InjectView(R.id.viewpager)
    ViewPager mViewPager;


    public static TabLayoutFragment newInstance() {
        return new TabLayoutFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.tab_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar(getString(R.string.tabLayout));
        setupTabLayout();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.tablayout_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
            AppBarLayout.LayoutParams tabParams =
                    (AppBarLayout.LayoutParams) mTabLayout.getLayoutParams();
        switch (item.getItemId()) {
            case R.id.menu_tab_pin_scroll:
                Snackbar.make(this.getView(), "You Selected TabLayout EnterAlways", Snackbar.LENGTH_SHORT).show();
                tabParams.setScrollFlags(0);
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_tab_unpin_scroll:
                Snackbar.make(this.getView(), "You Selected TabLayout Scroll", Snackbar.LENGTH_SHORT).show();
                tabParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL| AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
//            case R.id.menu_snakbar_fab_scale_anim:
//                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//                getActivity().supportInvalidateOptionsMenu();
//                return true;
//            case R.id.menu_snakbar_fab_translate_anim:
//                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//                getActivity().supportInvalidateOptionsMenu();
//                return true;
////            case R.id.menu_extended_toolbar_scroll:
////                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
////                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
////                getActivity().supportInvalidateOptionsMenu();
////                return true;
////            case R.id.menu_extended_toolbar_scroll_enteralways:
////                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
////                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
////                getActivity().supportInvalidateOptionsMenu();
////                return true;
////            case R.id.menu_extended_toolbar_scroll_enteralways_enteralwasyscollapsed:
////                Snackbar.make(mRootView, "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
////                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
////                getActivity().supportInvalidateOptionsMenu();
////                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        mViewPager.setAdapter(new ViewPagerAdapter
                (getChildFragmentManager(), mTabLayout.getTabCount()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
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
}
