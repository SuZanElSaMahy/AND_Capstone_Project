package com.suzanelsamahy.vidviewer.presenter;


import com.suzanelsamahy.vidviewer.playlists.PlayListItemResponse;
import com.suzanelsamahy.vidviewer.playlists.PlayListResponse;
import com.suzanelsamahy.vidviewer.util.ApiClient;
import com.suzanelsamahy.vidviewer.util.Constants;
import com.suzanelsamahy.vidviewer.util.PlayListCallBack;
import com.suzanelsamahy.vidviewer.view.IPlayListItemsView;
import com.suzanelsamahy.vidviewer.view.IPlayListView;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public class PlayListPresenter implements IPlayListPresenter, PlayListCallBack {

    private IPlayListView playListView;
    private IPlayListItemsView playListItemView;

    public PlayListPresenter(IPlayListView playListView , IPlayListItemsView playListItemView) {
        this.playListView = playListView;
        this.playListItemView = playListItemView;
    }


    @Override
    public void getPlaylistDetails(String channelId) {
        ApiClient.getInstance().getplaylistInfo(Constants.API_KEY, this, channelId, Constants.PART_VALUE,Constants.MAX_RESULTS_Value);
    }



//    @Override
//    public void getPlaylistItemsDetails(String playlistId) {
//        ApiClient.getInstance().getplaylistItemInfo(Constants.API_KEY, this, playlistId, Constants.PART_VALUE,Constants.MAX_RESULTS_Value);
//    }

    @Override
    public void onDestroy() {
        playListView = null;
        playListItemView = null;

    }


    @Override
    public void onPlayListRetrieveSuccess(Object object) {
        if (playListView == null) {
            return;
        }

        if (object != null && object instanceof PlayListResponse) {
            PlayListResponse response = (PlayListResponse) object;
            if (response.getItems() != null) {
                playListView.onPlayListsRetrieved(response.getItems());
            } else {
                playListView.onPlayListsError("Error no Movies");
            }
        } else {
            playListView.onPlayListsError("Error no Response");
        }
    }

    @Override
    public void onPlayListItemsRetrieveSuccess(Object object) {

        if (playListItemView == null) {
            return;
        }

        if (object != null && object instanceof PlayListItemResponse) {
            PlayListItemResponse response = (PlayListItemResponse) object;
            if (response.getItems() != null) {
                playListItemView.onPlayListItemsRetrieved(response.getItems());
            } else {
                playListItemView.onPlayListItemsError("Error no Movies");
            }
        } else {
            playListItemView.onPlayListItemsError("Error no Response");
        }

    }

    @Override
    public void onFailure(String message) {
        if (playListView != null) {
            playListView.onPlayListsError(message);
        }

        if (playListItemView != null) {
            playListItemView.onPlayListItemsError(message);
        }

    }
}
