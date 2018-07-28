package com.suzanelsamahy.vidviewer.util;


import com.suzanelsamahy.vidviewer.playlists.PlayListItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public interface PlayListItemsApi {
    @GET(Constants.PLAYLIST_ITEM_PARAM)

        // https://www.googleapis.com/youtube/v3/playlistItems?key=AIzaSyAcUj1l6Jz6ObCl2j6vtp7FzNQXEN2DpCE&playlistId=PLVAvctGvugB9ge-76R6Hs7c0VTjjsSVzY&part=snippet,id&maxResults=5
    Call<PlayListItemResponse> callPlayListItemApi(@Query(Constants.KEY_PARAM) String apiKey ,
                                                   @Query(Constants.PLAYLIST_ITEM_ID) String playlistId ,
                                                   @Query(Constants.PART_PARAM) String part,
                                                   @Query(Constants.MAX_RESULTS_PARAM) int result);

}
