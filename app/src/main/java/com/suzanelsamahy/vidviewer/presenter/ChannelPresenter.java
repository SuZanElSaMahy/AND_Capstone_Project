package com.suzanelsamahy.vidviewer.presenter;


import com.suzanelsamahy.vidviewer.channel.SearchResponse;
import com.suzanelsamahy.vidviewer.util.ApiClient;
import com.suzanelsamahy.vidviewer.util.ChannelCallBack;
import com.suzanelsamahy.vidviewer.view.IChannelView;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class ChannelPresenter implements ChannelCallBack, IChannelPresenter {

    private IChannelView channelView;


    public ChannelPresenter(IChannelView movieView) {
        this.channelView = movieView;

    }

    @Override
    public void getChannelDetails(String key, String channelId, String part, String order, int result) {
        ApiClient.getInstance().getChannelInfo(key,this, channelId,part,order,result);
    }

    @Override
    public void onDestroy() {
        channelView = null;
    }


    @Override
    public void onChannelRetrieveSuccess(Object object) {

        if (channelView == null) {
            return;
        }

        if (object != null && object instanceof SearchResponse) {
            SearchResponse response = (SearchResponse) object;
            if (response.getItems() != null) {
                channelView.onChannelInfoRetrieved(response.getItems());
            } else {
                channelView.onChannelError("Error no Movies");
            }
        } else {
            channelView.onChannelError("Error no Response");
        }

    }

    @Override
    public void onFailure(String message) {
        if(channelView != null){
            channelView.onChannelError(message);
        }
    }

}
