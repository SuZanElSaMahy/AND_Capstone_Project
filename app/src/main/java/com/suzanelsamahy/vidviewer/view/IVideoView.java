package com.suzanelsamahy.vidviewer.view;


import com.suzanelsamahy.vidviewer.video.Video;

import java.util.List;

/**
 * Created by suzanelsamahy on 3/18/18.
 */

public interface IVideoView {

    void onVideoInfoRetrieved(List<Video> video);
    void onVideoError(String Message);
}
