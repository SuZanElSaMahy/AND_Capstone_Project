package com.suzanelsamahy.vidviewer.presenter;


import com.suzanelsamahy.vidviewer.channel.SearchResponse;
import com.suzanelsamahy.vidviewer.util.ApiClient;
import com.suzanelsamahy.vidviewer.util.Constants;
import com.suzanelsamahy.vidviewer.util.VideoCallBack;
import com.suzanelsamahy.vidviewer.view.ISearchView;

/**
 * Created by suzanelsamahy on 3/19/18.
 */

public class SearchPresenter implements VideoCallBack, ISearchPresenter {

    private ISearchView searchView;

    public SearchPresenter(ISearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void onVideoRetrieveSuccess(Object object) {
        if (searchView == null) {
            return;
        }

        if (object != null && object instanceof SearchResponse) {
            SearchResponse response = (SearchResponse) object;
            if (response.getItems() != null) {
                searchView.onSearchResultRetrieved(response.getItems());
            } else {
                searchView.onSearchError("Error no Movies");
            }
        } else {
            searchView.onSearchError("Error no Response");
        }

    }

    @Override
    public void onFailure(String message) {

        if(searchView!=null){
            searchView.onSearchError(message);
        }
    }

    @Override
    public void getSearchDetails(String q, String channelId) {
//
//        Map<String, Object> objectMap = new HashMap<>();
//        objectMap.put(Constants.KEY_PARAM,Constants.API_KEY);
//        objectMap.put(Constants.CHANNEL_ID, Constants.CHANNEL_ID_VALUE);
//        objectMap.put(Constants.PART_PARAM, Constants.PART_VALUE);
//        objectMap.put(Constants.ORDER_PARAM, Constants.ORDER_KEY);
//        objectMap.put(Constants.MAX_RESULTS_PARAM,Constants.MAX_RESULTS_Value);

        ApiClient.getInstance().getSearchInfo(Constants.API_KEY,this,channelId, "snippet,id",Constants.ORDER_KEY,Constants.MAX_RESULTS_Value,q);
    }

    @Override
    public void onDestroy() {
        searchView = null;
    }
}
