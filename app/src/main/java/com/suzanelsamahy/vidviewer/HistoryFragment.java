package com.suzanelsamahy.vidviewer;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suzanelsamahy.vidviewer.adapters.VideoHistoryAdapter;
import com.suzanelsamahy.vidviewer.base.BaseFragment;
import com.suzanelsamahy.vidviewer.database.HistoryListViewModel;
import com.suzanelsamahy.vidviewer.database.HistoryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment {


    public HistoryFragment() {
        // Required empty public constructor
    }

    private VideoHistoryAdapter mAdapter;
    private OnHistoryItemInterface mListener;
    private HistoryListViewModel viewModel;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView(new ArrayList<HistoryModel>());
        viewModel = ViewModelProviders.of(this).get(HistoryListViewModel.class);
        viewModel.getItemAndPersonList().observe(getActivity(), new Observer<List<HistoryModel>>() {
            @Override
            public void onChanged(@Nullable List<HistoryModel> itemAndPeople) {
                mAdapter.updateMovies(itemAndPeople);
            }
        });

        return view;
    }

    private void setupRecyclerView(final List<HistoryModel> movies) {

        mAdapter = new VideoHistoryAdapter(movies, getActivity(), new VideoHistoryAdapter.OnChannelItemClickListener() {
            @Override
            public void onChannelItemClicked(HistoryModel item, int pos) {
                Intent intent = new Intent(getActivity(),VideoDetailActivity.class);
                intent.putExtra(getString(R.string.main_activity_intent_history_str),item);
                startActivity(intent);
                mListener.onHistoryItemClickListener(item,pos);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public interface OnHistoryItemInterface {
        void onHistoryItemClickListener(HistoryModel item, int pos);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHistoryItemInterface) {
            mListener = (OnHistoryItemInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LocationFragmentListener");
        }
    }
}
