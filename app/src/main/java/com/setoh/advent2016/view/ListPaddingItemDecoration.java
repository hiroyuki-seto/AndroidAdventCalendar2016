package com.setoh.advent2016.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.setoh.advent2016.R;

/**
 * Created by hiroyuki.seto on 2016/12/06.
 */

public class ListPaddingItemDecoration extends RecyclerView.ItemDecoration {

    private int mPadding;

    public void attach(RecyclerView recyclerView) {
        mPadding = recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.list_top_item_padding);
        recyclerView.addItemDecoration(this);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top += mPadding;
        } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.bottom += mPadding;
        }
    }
}