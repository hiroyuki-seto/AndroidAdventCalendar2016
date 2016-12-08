package com.setoh.advent2016.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setoh.advent2016.R;
import com.setoh.advent2016.databinding.ListItemDrawerBinding;

/**
 * Created by hiroyuki.seto on 2016/12/06.
 */

public class DrawerAdapter extends StringArrayAdapter<DrawerAdapter.ViewHolder> {

    private int mSelectedPosition = 0;

    public DrawerAdapter() {
    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, int position) {
        holder.mBinding.setString(get(position));
        holder.mBinding.setSelected(position == mSelectedPosition);
        holder.mBinding.executePendingBindings();
    }

    public void setSelected(int position) {
        this.mSelectedPosition = position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemDrawerBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = ListItemDrawerBinding.bind(itemView);
        }

        public ListItemDrawerBinding getBinding() {
            return mBinding;
        }
    }
}
