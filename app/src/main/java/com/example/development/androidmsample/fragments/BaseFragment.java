package com.example.development.androidmsample.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.development.androidmsample.MainActivity;
import com.example.development.androidmsample.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Shekar on 6/14/15.
 */

public abstract class BaseFragment extends Fragment {


    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.inject(this, view);
        return view;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract  @LayoutRes int getLayout();
}
