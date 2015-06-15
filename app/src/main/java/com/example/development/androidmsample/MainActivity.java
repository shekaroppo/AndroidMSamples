package com.example.development.androidmsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.development.androidmsample.adapter.ViewPagerAdapter;
import com.example.development.androidmsample.fragments.BaseFragment;
import com.example.development.androidmsample.fragments.TabFragment;
import com.example.development.androidmsample.utils.Navigator;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener
        , NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.navigation_view)
    NavigationView mNavigationView;


    private ActionBarDrawerToggle mDrawerToggle;
    private int mCurrentMenuItem;
    private static Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initNavigator();
        setupNavDrawer();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        setRootFragment(TabFragment.newInstance());
        mCurrentMenuItem = R.id.tabLayout;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
//            case R.id.standard_app_bar_menu_item:
//                setNewRootFragment(StandardAppBarFragment.newInstance());
//                break;
//            case R.id.tabs_menu_item:
//                setNewRootFragment(TabHolderFragment.newInstance());
//                break;
//
//            case R.id.parallax_menu_item:
//                setNewRootFragment(FlexibleSpaceWithImageFragment.newInstance());
//                break;
//
//            case R.id.pinned_app_bar_menu_item:
//                setNewRootFragment(FlexibleSpaceFragment.newInstance());
//                break;
        }
        mCurrentMenuItem = id;
        menuItem.setChecked(true);
        return false;
    }
}
