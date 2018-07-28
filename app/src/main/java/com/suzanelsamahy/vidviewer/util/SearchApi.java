package com.suzanelsamahy.vidviewer.util;


import com.suzanelsamahy.vidviewer.channel.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by suzanelsamahy on 3/19/18.
 */

public interface SearchApi {

    //    https://www.googleapis.com/youtube/v3/search?key=AIzaSyAcUj1l6Jz6ObCl2j6vtp7FzNQXEN2DpCE&channelId=UC-fT6wIb2N5-fZexR_eBRyA&part=snippet,id&order=date&maxResults=20

    @GET(Constants.SEARCH_URL)
    Call<SearchResponse> callSearchApi(@Query("key") String apiKey ,
                                       @Query("channelId") String channelId ,
                                       @Query(Constants.PART_PARAM) String part,
                                       @Query("order") String order,
                                       @Query("maxResults") int result ,
                                       @Query("q") String query);
}
