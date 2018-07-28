package com.suzanelsamahy.vidviewer.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.suzanelsamahy.vidviewer.playlists.PlayList;
import com.suzanelsamahy.vidviewer.playlists.PlayListItemResponse;
import com.suzanelsamahy.vidviewer.playlists.PlayListResponse;
import com.suzanelsamahy.vidviewer.presenter.IPlayListPresenter;
import com.suzanelsamahy.vidviewer.util.ApiClient;
import com.suzanelsamahy.vidviewer.util.Constants;
import com.suzanelsamahy.vidviewer.util.PlayListCallBack;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel implements PlayListCallBack {

    private MutableLiveData<List<PlayList>> heroList;
    private MutableLiveData<List<PlayList>> itemList;
    private IPlayListPresenter playListPresenter;
    private Context context;


    //we will call this method to get the data
    public LiveData<List<PlayList>> getHeroes(String chanelId) {
        //if the list is null
        if (heroList == null) {
            heroList = new MutableLiveData<List<PlayList>>();
            //we will load it asynchronously from server in this method
            loadHeroes(chanelId);
        }

        //finally we will return the list
        return heroList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadHeroes(String chanelId) {
        ApiClient.getInstance().getplaylistInfo(Constants.API_KEY, this, chanelId, Constants.PART_VALUE,Constants.MAX_RESULTS_Value);
//        playListPresenter = new PlayListPresenter(this,this);
//        playListPresenter.getPlaylistDetails(Constants.CHANNEL_ID_VALUE);
    }

    private void loadPlayListItems(String playlistId) {
        ApiClient.getInstance().getplaylistItemInfo(Constants.API_KEY, this, playlistId, Constants.PART_VALUE,Constants.MAX_RESULTS_Value);
    }

    @Override
    public void onPlayListRetrieveSuccess(Object object) {

        if (object != null && object instanceof PlayListResponse) {
            PlayListResponse response = (PlayListResponse) object;
            if (response.getItems() != null) {
                heroList.setValue(response.getItems());
            } else {
                heroList.setValue(new ArrayList<PlayList>());
            }
        }
    }

    @Override
    public void onPlayListItemsRetrieveSuccess(Object object) {
        if (object != null && object instanceof PlayListItemResponse) {
            PlayListItemResponse response = (PlayListItemResponse) object;
            if (response.getItems() != null) {
                itemList.setValue(response.getItems());
            } else {
                itemList.setValue(new ArrayList<PlayList>());
            }
        }
    }

    @Override
    public void onFailure(String message) {

    }
}
