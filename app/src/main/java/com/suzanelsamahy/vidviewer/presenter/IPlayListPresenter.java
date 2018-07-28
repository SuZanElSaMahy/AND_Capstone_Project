package com.suzanelsamahy.vidviewer.presenter;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public interface IPlayListPresenter {

    void getPlaylistDetails(String channelId);
//    void getPlaylistItemsDetails(String playlistId);
    void onDestroy();
}
