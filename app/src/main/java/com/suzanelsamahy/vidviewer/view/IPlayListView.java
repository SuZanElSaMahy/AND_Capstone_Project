package com.suzanelsamahy.vidviewer.view;


import com.suzanelsamahy.vidviewer.playlists.PlayList;

import java.util.List;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public interface IPlayListView {

    void onPlayListsRetrieved(List<PlayList> video);
    void onPlayListsError(String Message);

}
