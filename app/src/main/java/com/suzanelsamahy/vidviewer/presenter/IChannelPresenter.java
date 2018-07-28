package com.suzanelsamahy.vidviewer.presenter;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public interface IChannelPresenter {

    void getChannelDetails(String key , String channelId , String part , String order , int result);
    void onDestroy();
}
