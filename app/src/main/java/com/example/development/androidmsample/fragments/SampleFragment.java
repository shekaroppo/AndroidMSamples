package com.example.development.androidmsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.development.androidmsample.R;
import com.example.development.androidmsample.adapter.SampleAdapter;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SampleFragment extends Fragment {

    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_fragment, container, false);
        ButterKnife.inject(this, view);
        Log.d("===SampleFragment", "onCreateView");
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("===SampleFragment", "onViewCreated");
        Bundle args = this.getArguments();
        setupRecyclerView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("===SampleFragment", "onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("===SampleFragment", "onViewStateRestored");

    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new SampleAdapter(getActivity(),
                Arrays.asList(getResources().getStringArray(R.array.superheros))));
    }

}