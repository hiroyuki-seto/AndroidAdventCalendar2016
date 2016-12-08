package com.setoh.advent2016.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiroyuki.seto on 2016/12/06.
 */

public abstract class StringArrayAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final List<String> mItems;

    public StringArrayAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public String get(int position) {
        return mItems.get(position);
    }

    public void add(String item) {
        mItems.add(item);
    }
}
