package com.example.development.androidmsample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.development.androidmsample.R;
import com.example.development.androidmsample.utils.helper.ItemTouchHelperAdapter;
import com.example.development.androidmsample.utils.helper.ItemTouchHelperViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FooterBarAdapter
        extends RecyclerView.Adapter<FooterBarAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<String> mValues= new ArrayList<>();
    private List<Integer> mDrawables = new ArrayList<>();


    public interface OnStartDragListener {

        void onStartDrag(RecyclerView.ViewHolder viewHolder);
    }

    @Override
    public void onItemDismiss(int position) {
        mValues.remove(position);
        mDrawables.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mValues, fromPosition, toPosition);
        Collections.swap(mDrawables, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
    public FooterBarAdapter(Context context) {
        mValues.addAll(Arrays.asList(context.getResources().getStringArray(R.array.superheros)));
        setDrawables();
    }

    private void setDrawables() {
        mDrawables.add(0, R.drawable.superman);
        mDrawables.add(1, R.drawable.batman);
        mDrawables.add(2, R.drawable.spiderman);
        mDrawables.add(3, R.drawable.thor);
        mDrawables.add(4, R.drawable.haljordan);
        mDrawables.add(5, R.drawable.wonderwoman);
        mDrawables.add(6, R.drawable.captainamerica);
        mDrawables.add(7, R.drawable.martinmanhunter);
        mDrawables.add(8, R.drawable.dickgrayson);
        mDrawables.add(9, R.drawable.thing);
        mDrawables.add(10, R.drawable.humantorch);
        mDrawables.add(11, R.drawable.mrfantatstic);
        mDrawables.add(12, R.drawable.invisiblewoman);
        mDrawables.add(13, R.drawable.wallywest);
        mDrawables.add(14, R.drawable.kylerayner);
        mDrawables.add(15, R.drawable.superboy);
        mDrawables.add(16, R.drawable.leonardo);
        mDrawables.add(17, R.drawable.raphael);
        mDrawables.add(18, R.drawable.donatello);
        mDrawables.add(19, R.drawable.michelangelo);
        mDrawables.add(20, R.drawable.silversurfer);
        mDrawables.add(21, R.drawable.aquaman);
        mDrawables.add(22, R.drawable.greenarrow);
        mDrawables.add(23, R.drawable.barryallen);
        mDrawables.add(24, R.drawable.timdrake);
        mDrawables.add(25, R.drawable.supergirl);
        mDrawables.add(26, R.drawable.ironman);
        mDrawables.add(27, R.drawable.hercules);
        mDrawables.add(28, R.drawable.daredevil);
        mDrawables.add(29, R.drawable.orion);
        mDrawables.add(30, R.drawable.blackpanther);
        mDrawables.add(31, R.drawable.wolverine);

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
        // Start a drag whenever the handle view it touched
        Glide.with(holder.mImageView.getContext())
                .load(mDrawables.get(position))
                .into(holder.mImageView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final View mView;
        public final ImageView mImageView;
        public final ImageView mHandleView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mHandleView = (ImageView) view.findViewById(R.id.handleview);
            mTextView = (TextView) view.findViewById(R.id.name);

        }
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
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

