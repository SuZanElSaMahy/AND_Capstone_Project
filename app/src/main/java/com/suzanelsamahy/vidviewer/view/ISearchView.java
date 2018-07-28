package com.suzanelsamahy.vidviewer.view;


import com.suzanelsamahy.vidviewer.channel.Search;

import java.util.List;

/**
 * Created by suzanelsamahy on 3/19/18.
 */

public interface ISearchView {

   void onSearchResultRetrieved(List<Search> videos);
   void onSearchError(String message);
}
