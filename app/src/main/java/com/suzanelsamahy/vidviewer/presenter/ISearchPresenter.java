package com.suzanelsamahy.vidviewer.presenter;

/**
 * Created by suzanelsamahy on 3/19/18.
 */

public interface ISearchPresenter {

    void getSearchDetails(String q, String channelId);
    void onDestroy();

}
