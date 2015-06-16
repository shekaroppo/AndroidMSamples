package com.example.development.androidmsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.development.androidmsample.R;

import java.util.ArrayList;
import java.util.List;

public class SampleAdapter
        extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {

    private List<String> mValues;
    private List<Integer> mDrawables = new ArrayList<>();


    public SampleAdapter(Context context, List<String> items) {
        mValues = items;
        setDrawables();
    }

    private void setDrawables() {
        mDrawables.add(0, R.drawable.b1);
        mDrawables.add(1, R.drawable.b2);
        mDrawables.add(2, R.drawable.b3);
        mDrawables.add(3, R.drawable.b4);
        mDrawables.add(4, R.drawable.b5);
        mDrawables.add(5, R.drawable.b6);
        mDrawables.add(6, R.drawable.b7);
        mDrawables.add(7, R.drawable.b8);
        mDrawables.add(8, R.drawable.b9);
        mDrawables.add(9, R.drawable.b10);
        mDrawables.add(10, R.drawable.b11);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTextView.setText(mValues.get(position));
        Glide.with(holder.mImageView.getContext())
                .load(mDrawables.get(position))
                .into(holder.mImageView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.batman_img);
            mTextView = (TextView) view.findViewById(R.id.batman_quote);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

