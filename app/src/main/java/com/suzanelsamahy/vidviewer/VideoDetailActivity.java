package com.suzanelsamahy.vidviewer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.suzanelsamahy.vidviewer.base.BaseAppCompatActivity;
import com.suzanelsamahy.vidviewer.channel.Search;
import com.suzanelsamahy.vidviewer.database.AddVideoInHistoryViewModel;
import com.suzanelsamahy.vidviewer.database.HistoryModel;
import com.suzanelsamahy.vidviewer.playlists.PlayList;
import com.suzanelsamahy.vidviewer.presenter.IVideoPresenter;
import com.suzanelsamahy.vidviewer.presenter.VideoPresenter;
import com.suzanelsamahy.vidviewer.util.ConnectivityReciever;
import com.suzanelsamahy.vidviewer.util.Constants;
import com.suzanelsamahy.vidviewer.video.Video;
import com.suzanelsamahy.vidviewer.view.IVideoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoDetailActivity extends BaseAppCompatActivity implements IVideoView, ConnectivityReciever.ConnectivityReceiverListener {

    @BindView(R.id.video_title_tv)
    TextView videoTitle;

    @BindView(R.id.video_desc_tv)
    TextView descTv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;


    private String id;
    private IVideoPresenter videoPresenter;
    private AddVideoInHistoryViewModel addViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        videoPresenter = new VideoPresenter(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addViewModel = ViewModelProviders.of(this).get(AddVideoInHistoryViewModel.class);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {


            if(intent.getParcelableExtra(getString(R.string.main_activity_intent_playlist_str))!=null){

                PlayList playListItem = intent.getParcelableExtra(getString(R.string.main_activity_intent_playlist_str));
                id = playListItem.getSnippet().getResourceId().getVideoId();

                if (checkConnection()){
                    videoPresenter.getVideoDetails(Constants.API_KEY, id, Constants.PART_SNIPPET);
                    addViewModel.addVideoToDB(new HistoryModel(id,playListItem.getSnippet().getThumbnails().getMedium().getUrl(),
                            playListItem.getSnippet().getTitle(),playListItem.getSnippet().getDescription()));
                }

            } else if (intent.getParcelableExtra(getString(R.string.main_activity_intent_history_str))!=null){
                HistoryModel historyModel = intent.getParcelableExtra(getString(R.string.main_activity_intent_history_str));
                id = historyModel.getVideo_id();
                videoTitle.setText(historyModel.getVideo_title());
                descTv.setText(historyModel.getVideo_desc());
            } else if(intent.getParcelableExtra(getString(R.string.main_activity_search_intent_str))!=null){
                Search searchItem = intent.getParcelableExtra(getString(R.string.main_activity_search_intent_str));
                id = searchItem.getId().getVideoId();

                if(checkConnection()){
                    videoPresenter.getVideoDetails(Constants.API_KEY, id, Constants.PART_SNIPPET);
                    addViewModel.addVideoToDB(new HistoryModel(id,searchItem.getSnippet().getThumbnails().getMedium().getUrl(),
                            searchItem.getSnippet().getTitle(),searchItem.getSnippet().getDescription()));
                }
            }
            setupYouTubePlayerFragment(id);
        }

    }


    @OnClick(R.id.fab)
    public void onShareVideoClicked(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.youtube_share_video_link) +id);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_link_str)));
    }



    private void setupYouTubePlayerFragment(final String videoId) {
        YouTubePlayerSupportFragment youtubeFragment = (YouTubePlayerSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.youtube_player_fragment);

        youtubeFragment.initialize(Constants.API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(videoId);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

    @Override
    public void onVideoInfoRetrieved(List<Video> video) {

        if (video != null) {
            for (int i = 0; i < video.size(); i++) {
                videoTitle.setText(video.get(i).getSnippet().getTitle());
                descTv.setText(video.get(i).getSnippet().getDescription());
            }
        }
    }

    @Override
    public void onVideoError(String Message) {
        showToastMessage(Message);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReciever.isConnected();
        showSnack(isConnected);
        return isConnected;
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = getString(R.string.connected_to_internet_str);
            color = Color.RED;
        } else {
            message = getString(R.string.not_connected_to_internet_str);
            color = Color.RED;
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.next_btn), message, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            snackbar.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        ChannelApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
