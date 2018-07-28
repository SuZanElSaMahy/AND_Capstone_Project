package com.suzanelsamahy.vidviewer.presenter;

/**
 * Created by suzanelsamahy on 3/18/18.
 */

public interface IVideoPresenter {

    void getVideoDetails(String key , String Id , String part);
    void onDestroy();
}
