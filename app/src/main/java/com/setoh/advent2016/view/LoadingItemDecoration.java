package com.setoh.advent2016.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.setoh.advent2016.R;

import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;

/**
 * Created by hiroyuki.seto on 2016/12/07.
 */

public class LoadingItemDecoration extends RecyclerView.ItemDecoration implements Drawable.Callback {

    private IndeterminateProgressDrawable mProgress;
    private RecyclerView mParent;

    private int mCircleSize;

    public LoadingItemDecoration(Context context) {
        mCircleSize = context.getResources().getDimensionPixelSize(R.dimen.list_loading_progress_size);
    }

    public void attach(@NonNull RecyclerView parent) {
        parent.addItemDecoration(this);
        mProgress = new IndeterminateProgressDrawable(parent.getContext());
        mProgress.setCallback(this);
        final TypedValue value = new TypedValue();
        parent.getContext().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        mProgress.setTint(value.data);
        mProgress.start();
        mParent = parent;
    }

    public void detach(@NonNull RecyclerView parent) {
        mProgress.setCallback(null);
        mProgress.stop();
        mParent = null;
        parent.removeItemDecoration(this);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager manager = (LinearLayoutManager) parent.getLayoutManager();
        int currentLastVisibleIndex = manager.findLastVisibleItemPosition();
        if (currentLastVisibleIndex == parent.getAdapter().getItemCount() - 1) {
            int offsetTop = parent.findViewHolderForAdapterPosition(currentLastVisibleIndex).itemView.getBottom();
            int viewCenterX = (parent.getLeft() + parent.getRight()) / 2;
            mProgress.setBounds(viewCenterX - mCircleSize / 2, offsetTop,
                    viewCenterX + mCircleSize / 2, offsetTop + mCircleSize);
            mProgress.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.bottom += mCircleSize;
        }
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        if (mParent == null) {
            return;
        }

        final Rect dirty = who.getBounds();
        final int scrollX = mParent.getScrollX();
        final int scrollY = mParent.getScrollY();
        mParent.invalidate(dirty.left + scrollX, dirty.top + scrollY,
                dirty.right + scrollX, dirty.bottom + scrollY);
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        if (mParent == null) {
            return;
        }
        final long delay = when - SystemClock.uptimeMillis();
        mParent.postDelayed(what, delay);
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        if (mParent == null) {
            return;
        }
        mParent.removeCallbacks(what);
    }
}