package com.example.development.androidmsample.fragments;

/**
 * Created by Shekar on 4/20/16.
 */

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.development.androidmsample.R;
import com.example.development.androidmsample.adapter.FooterBarAdapter;
import com.example.development.androidmsample.utils.helper.SimpleItemTouchHelperCallback;

import butterknife.Bind;

public class FooterBarFragment extends BaseFragment {

    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Bind(R.id.appbar)
    AppBarLayout mAppBarLayout;

    public static FooterBarFragment newInstance() {
        return new FooterBarFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.footer_bar_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar(getString(R.string.toolbarLayout));
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        FooterBarAdapter adapter = new FooterBarAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
    }

}
