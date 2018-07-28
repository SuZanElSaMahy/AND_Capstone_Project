package com.suzanelsamahy.vidviewer.util;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public interface PlayListCallBack {

    void onPlayListRetrieveSuccess(Object object);
    void onPlayListItemsRetrieveSuccess(Object object);
    void onFailure(String message);

}
