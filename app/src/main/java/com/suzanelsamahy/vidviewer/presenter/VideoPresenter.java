package com.suzanelsamahy.vidviewer.presenter;


import com.suzanelsamahy.vidviewer.util.ApiClient;
import com.suzanelsamahy.vidviewer.util.Constants;
import com.suzanelsamahy.vidviewer.util.VideoCallBack;
import com.suzanelsamahy.vidviewer.video.VideoResponse;
import com.suzanelsamahy.vidviewer.view.IVideoView;

/**
 * Created by suzanelsamahy on 3/18/18.
 */

public class VideoPresenter implements VideoCallBack, IVideoPresenter {

    private IVideoView videoView;

    public VideoPresenter(IVideoView videoView) {
        this.videoView = videoView;
    }

    @Override
    public void getVideoDetails(String key, String videoId, String part) {
        ApiClient.getInstance().getVideoInfo(Constants.API_KEY,this,videoId,Constants.PART_VALUE);
    }

    @Override
    public void onDestroy() {
        videoView = null;

    }

    @Override
    public void onVideoRetrieveSuccess(Object object) {

        if (videoView == null) {
            return;
        }

        if (object != null && object instanceof VideoResponse) {
            VideoResponse response = (VideoResponse) object;
            if (response.getItems() != null) {
                videoView.onVideoInfoRetrieved(response.getItems());
            } else {
                videoView.onVideoError("Error no Movies");
            }
        } else {
            videoView.onVideoError("Error no Response");
        }

    }

    @Override
    public void onFailure(String message) {
          if(videoView!=null){
              videoView.onVideoError(message);
          }
    }
}
