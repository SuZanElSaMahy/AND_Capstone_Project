package com.suzanelsamahy.vidviewer.util;

import android.util.Log;

import com.suzanelsamahy.vidviewer.channel.SearchResponse;
import com.suzanelsamahy.vidviewer.playlists.PlayListItemResponse;
import com.suzanelsamahy.vidviewer.playlists.PlayListResponse;
import com.suzanelsamahy.vidviewer.video.VideoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class ApiClient {

    private static YouTubeApi youTubeApi = null;
    private static VideoApi videoApi = null;
    private static SearchApi searchApi = null;
    private static PlayListApi playlistApi = null;
    private static PlayListItemsApi playlistItemApi = null;
    private static ApiClient apiClient;


    public static synchronized ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
            return apiClient;
        } else {
            return apiClient;
        }
    }

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        youTubeApi = retrofit.create(YouTubeApi.class);
        videoApi = retrofit.create(VideoApi.class);
        searchApi = retrofit.create(SearchApi.class);
        playlistApi = retrofit.create(PlayListApi.class);
        playlistItemApi = retrofit.create(PlayListItemsApi.class);
    }



    public void getChannelInfo(String key, final ChannelCallBack channelCallBack, String channelId, String part , String order , int result) {
        Callback<SearchResponse> movieResponseCallback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null) {
                    channelCallBack.onChannelRetrieveSuccess(response.body());
                } else {
                    channelCallBack.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                t.printStackTrace();
                channelCallBack.onFailure(t.getMessage());
                Log.d("api",""+t.getMessage());
            }
        };

        Call<SearchResponse> getMovieResponseCall = youTubeApi.callYouTubeChannelApi(key,channelId,part,order,result);
        getMovieResponseCall.enqueue(movieResponseCallback);
    }


    public void getplaylistInfo(String key, final PlayListCallBack videoCallBack, String Id, String part , int result) {
        Callback<PlayListResponse> movieResponseCallback = new Callback<PlayListResponse>() {
            @Override
            public void onResponse(Call<PlayListResponse> call, Response<PlayListResponse> response) {
                if (response.body() != null) {
                    videoCallBack.onPlayListRetrieveSuccess(response.body());
                } else {
                    videoCallBack.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<PlayListResponse> call, Throwable t) {
                t.printStackTrace();
                videoCallBack.onFailure(t.getMessage());
                Log.d("api",""+t.getMessage());
            }
        };

        Call<PlayListResponse> getMovieResponseCall = playlistApi.callPlayListApi(key,Id,part,result);
        getMovieResponseCall.enqueue(movieResponseCallback);
    }



    public void getplaylistItemInfo(String key, final PlayListCallBack videoCallBack, String Id, String part , int result) {
        Callback<PlayListItemResponse> movieResponseCallback = new Callback<PlayListItemResponse>() {
            @Override
            public void onResponse(Call<PlayListItemResponse> call, Response<PlayListItemResponse> response) {
                if (response.body() != null) {
                    videoCallBack.onPlayListItemsRetrieveSuccess(response.body());
                } else {
                    videoCallBack.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<PlayListItemResponse> call, Throwable t) {
                t.printStackTrace();
                videoCallBack.onFailure(t.getMessage());
                Log.d("api",""+t.getMessage());
            }
        };

        Call<PlayListItemResponse> getMovieResponseCall = playlistItemApi.callPlayListItemApi(key,Id,part,result);
        getMovieResponseCall.enqueue(movieResponseCallback);
    }





    public void getVideoInfo(String key, final VideoCallBack videoCallBack, String Id, String part) {
        Callback<VideoResponse> movieResponseCallback = new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.body() != null) {
                    videoCallBack.onVideoRetrieveSuccess(response.body());
                } else {
                    videoCallBack.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                t.printStackTrace();
                videoCallBack.onFailure(t.getMessage());
                Log.d("api",""+t.getMessage());
            }
        };

        Call<VideoResponse> getMovieResponseCall = videoApi.callVideoApi(key,Id,part);
        getMovieResponseCall.enqueue(movieResponseCallback);
    }


    public void getSearchInfo(String key, final VideoCallBack videoCallBack, String Id, String part , String order , int result , String query) {
        Callback<SearchResponse> movieResponseCallback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null) {
                    videoCallBack.onVideoRetrieveSuccess(response.body());
                } else {
                    videoCallBack.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                t.printStackTrace();
                videoCallBack.onFailure(t.getMessage());
                Log.d("api",""+t.getMessage());
            }
        };

        Call<SearchResponse> getMovieResponseCall = searchApi.callSearchApi(key,Id,part,order,result,query);
        getMovieResponseCall.enqueue(movieResponseCallback);
    }

}
