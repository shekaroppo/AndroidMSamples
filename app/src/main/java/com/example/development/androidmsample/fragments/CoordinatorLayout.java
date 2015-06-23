package com.example.development.androidmsample.fragments;

import android.os.Bundle;
import android.view.View;

import com.example.development.androidmsample.R;

public class CoordinatorLayout extends BaseFragment {


    @Override
    protected int getLayout() {
        return R.layout.collapsing_toolbar_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        loadBackdrop();
    }

    private void loadBackdrop() {
//        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
    }

    public static CoordinatorLayout newInstance() {
        return new CoordinatorLayout();
    }
}
