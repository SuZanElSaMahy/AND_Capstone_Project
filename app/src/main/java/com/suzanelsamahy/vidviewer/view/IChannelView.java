package com.suzanelsamahy.vidviewer.view;


import com.suzanelsamahy.vidviewer.channel.Search;

import java.util.List;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public interface IChannelView {

    void onChannelInfoRetrieved(List<Search> movies);
    void onChannelError(String Message);

}
