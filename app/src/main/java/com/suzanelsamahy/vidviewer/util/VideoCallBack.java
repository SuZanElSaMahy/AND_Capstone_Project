package com.suzanelsamahy.vidviewer.util;

/**
 * Created by suzanelsamahy on 3/18/18.
 */

public interface VideoCallBack {

    void onVideoRetrieveSuccess(Object object);
    void onFailure(String message);
}
