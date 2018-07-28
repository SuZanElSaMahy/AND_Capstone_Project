package com.suzanelsamahy.vidviewer.util;


import com.suzanelsamahy.vidviewer.playlists.PlayListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by suzanelsamahy on 3/20/18.
 */

public interface PlayListApi {

    // https://www.googleapis.com/youtube/v3/playlists?key=AIzaSyAcUj1l6Jz6ObCl2j6vtp7FzNQXEN2DpCE&channelId=UC-fT6wIb2N5-fZexR_eBRyA&part=snippet,id&maxResults=20

    @GET(Constants.PLAYLIST_PARAM)
    Call<PlayListResponse> callPlayListApi(@Query(Constants.KEY_PARAM) String apiKey ,
                                           @Query(Constants.CHANNEL_ID) String channelId ,
                                           @Query(Constants.PART_PARAM) String part,
                                           @Query(Constants.MAX_RESULTS_PARAM) int result) ;

}
