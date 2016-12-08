package com.setoh.advent2016;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.setoh.advent2016.adapter.DrawerAdapter;
import com.setoh.advent2016.view.ListPaddingItemDecoration;

/**
 * Created by hiroyuki.seto on 2016/12/08.
 */

public class DrawerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TypedValue value = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.selectableItemBackground, value, true);
        final Drawable top = ContextCompat.getDrawable(getActivity(), value.resourceId);
        Drawable back = ContextCompat.getDrawable(getActivity(), R.drawable.bg_checkable_list_item);
        final Drawable itemBackground = new LayerDrawable(new Drawable[]{back, top});

        RecyclerView drawer = (RecyclerView) getView();
        drawer.setLayoutManager(new LinearLayoutManager(getActivity()));
        new ListPaddingItemDecoration().attach(drawer);
        final DrawerAdapter adapter = new DrawerAdapter() {
            @Override
            public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final DrawerAdapter.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                final CheckedTextView text = viewHolder.getBinding().itemText;
                text.setBackground(itemBackground.getConstantState().newDrawable());
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = viewHolder.getAdapterPosition();
                        if (position == RecyclerView.NO_POSITION) {
                            return;
                        }
                        setSelected(position);
                        notifyDataSetChanged();
                    }
                });
                return viewHolder;
            }
        };
        for (int i = 0; i < 20; i++) {
            adapter.add(getString(R.string.menu_item, i));
        }
        drawer.setAdapter(adapter);
    }
}
