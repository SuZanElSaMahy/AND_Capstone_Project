package com.suzanelsamahy.vidviewer.util;


import com.suzanelsamahy.vidviewer.channel.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by suzanelsamahy on 3/14/18.
 */

public interface YouTubeApi {

 //   https://www.googleapis.com/youtube/v3/channels?key=AIzaSyAcUj1l6Jz6ObCl2j6vtp7FzNQXEN2DpCE&id=UC_x5XG1OV2P6uZZ5FSM9Ttw&part=snippet,id&maxResults=5

    @GET(Constants.SEARCH_URL)
    Call<SearchResponse> callYouTubeChannelApi(@Query(Constants.KEY_PARAM) String apiKey ,
                                               @Query(Constants.CHANNEL_ID) String channelId ,
                                               @Query(Constants.PART_PARAM) String part,
                                               @Query(Constants.ORDER_PARAM) String order,
                                               @Query(Constants.MAX_RESULTS_PARAM) int result) ;

}
