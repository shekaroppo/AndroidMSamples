package com.example.development.androidmsample;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.development.androidmsample.fragments.BaseFragment;
import com.example.development.androidmsample.fragments.FooterBarFragment;
import com.example.development.androidmsample.fragments.PercentRelativeLayoutFragment;
import com.example.development.androidmsample.fragments.TabLayoutFragment;
import com.example.development.androidmsample.fragments.ToolbarFragment;
import com.example.development.androidmsample.utils.Navigator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener
        , NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;


    private ActionBarDrawerToggle mDrawerToggle;
    private int mCurrentMenuItem;
    private static Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavigator();
        setupNavDrawer();
        if(savedInstanceState==null){
            setRootFragment(ToolbarFragment.newInstance());
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    private void initNavigator() {
        if (mNavigator != null) return;
        mNavigator = new Navigator(getSupportFragmentManager(), R.id.container);
    }

    private void setRootFragment(BaseFragment fragment) {
        mNavigator.setRootFragment(fragment);
        mDrawerLayout.closeDrawers();
    }

    private void setupNavDrawer() {
        mDrawerLayout.setDrawerListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this
                , mDrawerLayout
                , null
                , R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        mDrawerToggle.onDrawerOpened(drawerView);
    }

    public void openDrawer(){
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mDrawerToggle.onDrawerClosed(drawerView);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        mDrawerToggle.onDrawerStateChanged(newState);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == mCurrentMenuItem) {
            mDrawerLayout.closeDrawers();
            return false;
        }
        switch (id) {
            case R.id.toolbarLayout:
                setRootFragment(ToolbarFragment.newInstance());
                break;
            case R.id.tabLayout:
                setRootFragment(TabLayoutFragment.newInstance());
                break;
            case R.id.percentRelativeLayout:
                setRootFragment(PercentRelativeLayoutFragment.newInstance());
                break;
            case R.id.footerQuickReturn:
                setRootFragment(FooterBarFragment.newInstance());
                break;
        }
        mCurrentMenuItem = id;
        menuItem.setChecked(true);
        return false;
    }
}
