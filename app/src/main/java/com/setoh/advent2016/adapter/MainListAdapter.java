package com.setoh.advent2016.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setoh.advent2016.R;
import com.setoh.advent2016.databinding.ListItemMainBinding;

/**
 * Created by hiroyuki.seto on 2016/12/06.
 */

public class MainListAdapter extends StringArrayAdapter<MainListAdapter.ViewHolder> {

    public MainListAdapter() {
    }

    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MainListAdapter.ViewHolder holder, int position) {
        holder.mBinding.setString(get(position));
        holder.mBinding.executePendingBindings();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemMainBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = ListItemMainBinding.bind(itemView);
        }
    }
}
