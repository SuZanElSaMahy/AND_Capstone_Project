package com.suzanelsamahy.vidviewer;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suzanelsamahy.vidviewer.adapters.SearchAdapter;
import com.suzanelsamahy.vidviewer.adapters.VideoAdapter;
import com.suzanelsamahy.vidviewer.base.BaseFragment;
import com.suzanelsamahy.vidviewer.channel.Search;
import com.suzanelsamahy.vidviewer.playlists.PlayList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;


    private VideoAdapter mAdapter;
    private OnPlayListItemInterface mListener;


    public MainFragment() {
        // Required empty public constructor
    }

    ArrayList<PlayList> playLists;

    public static MainFragment getInstance(ArrayList<PlayList> playList) {
        MainFragment fragment = new MainFragment();
        fragment.playLists = playList;
        return fragment;
    }

    private Parcelable listState;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        if(playLists!=null&& playLists.size()>0){
            setupRecyclerView(playLists);
        } else {
            setupRecyclerView(new ArrayList<PlayList>());
        }
        if (savedInstanceState != null) {
            playLists = savedInstanceState.getParcelableArrayList(getString(R.string.main_fragment_state_playlist_str));
            listState=savedInstanceState.getParcelable("ListState");
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
            mAdapter = new VideoAdapter(playLists, getActivity(), new VideoAdapter.OnPlaylistItemClickListener() {
                @Override
                public void onChannelItemClicked(PlayList item, int pos) {
                    mListener.onPlayListItemClickListener(item, pos);
                }
            });
            recyclerView.setAdapter(mAdapter);
        }
        setupChannelRecyclerView(new ArrayList<Search>());




        return view;
    }


    private boolean isSearch = false;
    private void setupRecyclerView(final List<PlayList> movies) {
        if(!isSearch){
            mAdapter = new VideoAdapter(movies, getActivity(), new VideoAdapter.OnPlaylistItemClickListener() {
                @Override
                public void onChannelItemClicked(PlayList item, int pos) {
                    mListener.onPlayListItemClickListener(item, pos);
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
            isSearch= true;
        }

    }


    private SearchAdapter searchAdapter;
    private void setupChannelRecyclerView(List<Search> movies) {

        if(isSearch){
            searchAdapter = new SearchAdapter(movies, getActivity(), new SearchAdapter.OnChannelItemClickListener() {
                @Override
                public void onChannelItemClicked(Search item, int position) {
                    mListener.onSearchItemClicked(item,position);
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView2.setLayoutManager(mLayoutManager);
            recyclerView2.setItemAnimator(new DefaultItemAnimator());
            recyclerView2.setAdapter(searchAdapter);
            isSearch = false;
        }
    }



    public void updateSearchVideos(List<Search> movies , boolean one) {
        hideProgressDialog();
        isSearch = one;
        if (searchAdapter != null && movies != null) {
            recyclerView.setVisibility(View.GONE);
            recyclerView2.setVisibility(View.VISIBLE);
            searchAdapter.updateMovies(movies);
        } else {
            showToastMessage(getString(R.string.main_fragment_no_search_result_str));
            setupChannelRecyclerView(movies);
        }
    }


    public void updateFragmentWithPlaylist(List<PlayList> movies, boolean one) {
        isSearch = one;
        playLists = (ArrayList)movies;
        if (mAdapter != null && movies != null) {
            recyclerView2.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            mAdapter.updateMovies(movies);
        } else {
          showToastMessage(getString(R.string.no_result_str));
            setupRecyclerView(movies);
        }
    }

    public interface OnPlayListItemInterface {
        void onPlayListItemClickListener(PlayList item, int pos);
        void onSearchItemClicked(Search item, int pos);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlayListItemInterface) {
            mListener = (OnPlayListItemInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LocationFragmentListener");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.main_fragment_state_playlist_str),  playLists);
        outState.putParcelable("ListState", recyclerView.getLayoutManager().onSaveInstanceState());
    }




}