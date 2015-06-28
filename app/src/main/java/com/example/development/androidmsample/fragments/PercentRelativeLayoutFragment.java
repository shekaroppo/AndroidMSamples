package com.example.development.androidmsample.fragments;

/**
 * Created by Shekar on 6/28/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.development.androidmsample.R;


/**
 * Created by Shekar on 6/26/15.
 */
public class PercentRelativeLayoutFragment extends BaseFragment {


    public static PercentRelativeLayoutFragment newInstance() {
        return new PercentRelativeLayoutFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.percent_relative_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar(getString(R.string.percent_relative_layout));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.tablayout_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
