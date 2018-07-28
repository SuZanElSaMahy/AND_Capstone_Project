package com.suzanelsamahy.vidviewer.util;


import com.suzanelsamahy.vidviewer.video.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public interface VideoApi {

//https://www.googleapis.com/youtube/v3/videos?key=AIzaSyAcUj1l6Jz6ObCl2j6vtp7FzNQXEN2DpCE&id=Ks-_Mh1QhMc&part=snippet,contentDetails,statistics

    @GET(Constants.VIDEO_URL)
    Call<VideoResponse> callVideoApi(@Query(Constants.KEY_PARAM) String apiKey ,
                                     @Query("id") String videoId ,
                                     @Query(Constants.PART_PARAM) String part) ;


}
