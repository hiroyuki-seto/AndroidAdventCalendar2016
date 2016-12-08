package com.setoh.advent2016;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setoh.advent2016.adapter.MainListAdapter;
import com.setoh.advent2016.view.LoadingItemDecoration;

/**
 * Created by hiroyuki.seto on 2016/12/08.
 */

public class ContentFragment extends Fragment {

    private LoadingItemDecoration mDecoration;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView list = (RecyclerView) getView();
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        //new ListPaddingItemDecoration().attach(list);
        mDecoration = new LoadingItemDecoration(getContext());
        mDecoration.attach(list);
        list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        MainListAdapter adapter = new MainListAdapter();
        for (int i = 0; i < 20; i++) {
            adapter.add(getString(R.string.list_item, i));
        }
        list.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDecoration.detach((RecyclerView) getView());
    }
}
