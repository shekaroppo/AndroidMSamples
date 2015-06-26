package com.example.development.androidmsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.development.androidmsample.R;
import com.example.development.androidmsample.adapter.SampleAdapter;
import com.example.development.androidmsample.utils.Constants;
import com.example.development.androidmsample.utils.ScrollAwareFABBehavior;

import java.util.Arrays;

import butterknife.InjectView;

public class ToolbarFragment extends BaseFragment {

    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @InjectView(R.id.fab)
    FloatingActionButton mFab;

    public static ToolbarFragment newInstance() {
        return new ToolbarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.toolbar_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar(getString(R.string.toolbarLayout));
        setupRecyclerView();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mRecyclerView, "Snackbar With Action!", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppBarLayout.LayoutParams toolbarParams =
                (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        CoordinatorLayout.LayoutParams fabParams = (CoordinatorLayout.LayoutParams)mFab.getLayoutParams();

        switch (item.getItemId()) {
            case R.id.menu_scroll:
                Snackbar.make(this.getView(), "You Selected Toolbar Scroll", Snackbar.LENGTH_SHORT).show();
                toolbarParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_scroll_enteralways:
                Snackbar.make(this.getView(), "You Selected Toolbar EnterAlways", Snackbar.LENGTH_SHORT).show();
                toolbarParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                getActivity().supportInvalidateOptionsMenu();
                return true;
            case R.id.menu_snakbar_fab_scale_anim:
                Snackbar.make(this.getView(), "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
                fabParams.setBehavior(new ScrollAwareFABBehavior(Constants.SCALE));
                mFab.setLayoutParams(fabParams);
                return true;
            case R.id.menu_snakbar_fab_translate_anim:
                Snackbar.make(this.getView(), "You Selected EnterAlways", Snackbar.LENGTH_SHORT).show();
                fabParams.setBehavior(new ScrollAwareFABBehavior(Constants.TRANSLATE));
                mFab.setLayoutParams(fabParams);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new SampleAdapter(getActivity(),
                Arrays.asList(getResources().getStringArray(R.array.superheros))));
    }

}
