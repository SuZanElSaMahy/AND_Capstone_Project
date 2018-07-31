package com.suzanelsamahy.vidviewer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.suzanelsamahy.vidviewer.base.BaseAppCompatActivity;
import com.suzanelsamahy.vidviewer.channel.Search;
import com.suzanelsamahy.vidviewer.database.HistoryModel;
import com.suzanelsamahy.vidviewer.playlists.PlayList;
import com.suzanelsamahy.vidviewer.presenter.IPlayListItemsPresenter;
import com.suzanelsamahy.vidviewer.presenter.IPlayListPresenter;
import com.suzanelsamahy.vidviewer.presenter.ISearchPresenter;
import com.suzanelsamahy.vidviewer.presenter.PlayListItemsPresenter;
import com.suzanelsamahy.vidviewer.presenter.SearchPresenter;
import com.suzanelsamahy.vidviewer.util.ConnectivityReciever;
import com.suzanelsamahy.vidviewer.util.LocaleHelper;
import com.suzanelsamahy.vidviewer.util.SharedPreferencesManager;
import com.suzanelsamahy.vidviewer.view.IPlayListItemsView;
import com.suzanelsamahy.vidviewer.view.ISearchView;
import com.suzanelsamahy.vidviewer.view.MainViewModel;
import com.suzanelsamahy.vidviewer.widget.ChannelWidgetService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        IPlayListItemsView,
        ConnectivityReciever.ConnectivityReceiverListener,
        MainFragment.OnPlayListItemInterface,
        HistoryFragment.OnHistoryItemInterface{


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.adView)
    AdView mAdview;

    private SearchView searchView;
    private ISearchPresenter searchPresenter;
    private IPlayListPresenter playListPresenter;
    private IPlayListItemsPresenter playListItemsPresenter;
    private SubMenu subMenu;
    private ArrayList<PlayList> playLists = new ArrayList<>();
    private MainViewModel model;
    private String channelId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


       if(!checkConnection()){
           hideProgressDialog();
       }



        MobileAds.initialize(this, getString(R.string.google_ads_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

//        playListPresenter = new PlayListPresenter(this,this);
//        playListPresenter.getPlaylistDetails(Constants.CHANNEL_ID_VALUE);


        playListItemsPresenter = new PlayListItemsPresenter(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        setupSearchView();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.getStringExtra(getString(R.string.channel_intent_str)) != null) {
                channelId = intent.getStringExtra(getString(R.string.channel_intent_str));
                if (channelId != null) {
                    setupViewModel(channelId);
                }
            }
        }
        String prefId = SharedPreferencesManager.getInstance(getApplicationContext()).getStringPref(SharedPreferencesManager.CHANNEL_ID);
        if (prefId != null && !prefId.isEmpty()) {
            channelId = prefId;
            setupViewModel(channelId);
        }

        if (savedInstanceState != null) {
            playLists = savedInstanceState.getParcelableArrayList(getString(R.string.main_activity_play_state_str));
            idd = savedInstanceState.getString(getString(R.string.main_activity_id_state_str));
            playListItemsPresenter.getPlaylistItemsDetails(idd);
//            ((MainFragment) viewPager.getAdapter().instantiateItem(viewPager, 0)).updateFragmentWithPlaylist(videoItems, false);
        }

    }


    private void setupViewModel(String id) {
        model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.getHeroes(id).observe(this, new Observer<List<PlayList>>() {
            @Override
            public void onChanged(@Nullable List<PlayList> heroList) {
                playLists = (ArrayList) heroList;
                final Menu menu = navigationView.getMenu();
                subMenu = menu.addSubMenu(R.string.nav_playlist_str);
                SharedPreferencesManager.getInstance(getApplicationContext()).saveStringPref(SharedPreferencesManager.CHANNEL_NAME, playLists.get(0).getSnippet().getChannelTitle());
                ChannelWidgetService.startChannelWidget(getApplicationContext());
                for (int i = 0; i < heroList.size(); i++) {
                    subMenu.add(0, Menu.FIRST + i, Menu.FIRST, heroList.get(i).getSnippet().getTitle())
                            .setIcon(R.drawable.ic_menu_slideshow);
                }
            }
        });
    }


    private void setupSearchView() {
        searchView = (SearchView) toolbar.findViewById(R.id.search_bar);
        searchView.setLayoutParams(new Toolbar.LayoutParams(Gravity.END));
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(isConnectedToInternet()){
                    hideKeyboard();
                    showProgressDialog();
                    new SearchTask(query).execute();
                } else {
                    showToastMessage(getString(R.string.not_connected_to_internet_str));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        ImageView closeButton = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                searchView.onActionViewCollapsed();
                searchView.setQuery("", false);
                searchView.clearFocus();
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_localize) {


            if (LocaleHelper.getLanguage(this).equals("en")) {
                LocaleHelper.setLocale(MainActivity.this, "ar");
            } else {
                LocaleHelper.setLocale(MainActivity.this, "en");
            }
            //  this.recreate();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            return true;
        } else if (id == R.id.action_channel) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
            // set title
            alertDialogBuilder.setTitle(R.string.main_activity_dialog_leave_str);
            // set dialog message
            alertDialogBuilder
                    .setMessage(R.string.exit_str)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes_str, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                            Intent intent = new Intent(MainActivity.this, EnterChannelIdActivity.class);
                            startActivity(intent);
                            SharedPreferencesManager.getInstance(getApplicationContext()).removeKey(SharedPreferencesManager.CHANNEL_ID);
                        }
                    })
                    .setNegativeButton(R.string.no_str, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    private String idd;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (playLists != null && playLists.size() > 0) {
            for (int i = 0; i < playLists.size(); i++) {
                if (id == subMenu.getItem(i).getItemId()) {
                    // Magic Here
                    //  showToastMessage(""+playLists.get(i).getId());
                    idd = playLists.get(i).getId();
                    playListItemsPresenter.getPlaylistItemsDetails(idd);
                }
            }
        }
        viewPager.setCurrentItem(0);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setupViewPager(ViewPager viewPager) {
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(MainFragment.getInstance(playLists), "ONE");
        adapter.addFrag(new HistoryFragment(), "TWO");
        viewPager.setAdapter(adapter);
    }


    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(R.string.trend_str);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_trend, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(R.string.history_str);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_history, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }


    @Override
    public void onPlayListItemClickListener(PlayList item, int pos) {
        Intent intent = new Intent(this, VideoDetailActivity.class);
        intent.putExtra(getString(R.string.main_activity_intent_playlist_str), item);
        startActivity(intent);
    }

    private List<PlayList> videoItems = new ArrayList<>();
    @Override
    public void onPlayListItemsRetrieved(List<PlayList> video) {
        hideProgressDialog();
        //videoItems = video;
        ((MainFragment) viewPager.getAdapter().instantiateItem(viewPager, 0)).updateFragmentWithPlaylist(video, false);
    }

    @Override
    public void onPlayListItemsError(String Message) {
        hideProgressDialog();
        showToastMessage(Message);
    }


    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putParcelableArrayList(getString(R.string.main_activity_play_state_str), (ArrayList) playLists);
        state.putString(getString(R.string.main_activity_id_state_str), idd);
    }

    @Override
    public void onSearchItemClicked(Search item, int pos) {
        Intent intent = new Intent(this, VideoDetailActivity.class);
        intent.putExtra(getString(R.string.main_activity_search_intent_str), item);
        startActivity(intent);
    }

    @Override
    public void onHistoryItemClickListener(HistoryModel item, int pos) {
        Intent intent = new Intent(this, VideoDetailActivity.class);
        intent.putExtra(getString(R.string.main_activity_intent_history_str), item);
        startActivity(intent);
    }


    private class SearchTask extends AsyncTask<String, Void, List<PlayList>> {
        String queryy;

        public SearchTask(String query) {
            this.queryy = query;
        }

        @Override
        protected List<PlayList> doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            searchPresenter = new SearchPresenter(new ISearchView() {
                @Override
                public void onSearchResultRetrieved(List<Search> videos) {
                    hideProgressDialog();
                    ((MainFragment) viewPager.getAdapter().instantiateItem(viewPager, 0)).updateSearchVideos(videos, true);
                    viewPager.setCurrentItem(0);
                }

                @Override
                public void onSearchError(String message) {
                    hideProgressDialog();
                    showToastMessage(getString(R.string.no_result_str));
                }
            });
            searchPresenter.getSearchDetails(queryy, channelId);
            return null;
        }
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
        hideProgressDialog();
        if (isConnected) {
            message = getString(R.string.connected_to_internet_str);
            color = Color.RED;
        } else {
            message = getString(R.string.not_connected_to_internet_str);
            color = Color.RED;
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

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

