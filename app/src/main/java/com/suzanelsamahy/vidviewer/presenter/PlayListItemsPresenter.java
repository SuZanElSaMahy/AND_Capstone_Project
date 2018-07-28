package com.suzanelsamahy.vidviewer.presenter;


import com.suzanelsamahy.vidviewer.playlists.PlayListItemResponse;
import com.suzanelsamahy.vidviewer.util.ApiClient;
import com.suzanelsamahy.vidviewer.util.Constants;
import com.suzanelsamahy.vidviewer.util.PlayListCallBack;
import com.suzanelsamahy.vidviewer.view.IPlayListItemsView;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public class PlayListItemsPresenter implements PlayListCallBack,IPlayListItemsPresenter{

    private IPlayListItemsView playListItemView;

    public PlayListItemsPresenter(IPlayListItemsView playListItemView) {
        this.playListItemView = playListItemView;
    }

    @Override
    public void getPlaylistItemsDetails(String playlistId) {
        ApiClient.getInstance().getplaylistItemInfo(Constants.API_KEY, this, playlistId, Constants.PART_VALUE,Constants.MAX_RESULTS_Value);
    }


    @Override
    public void onDestroy() {
        playListItemView = null;

    }

    @Override
    public void onPlayListRetrieveSuccess(Object object) {

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

        if (playListItemView != null) {
            playListItemView.onPlayListItemsError(message);
        }

    }

}
