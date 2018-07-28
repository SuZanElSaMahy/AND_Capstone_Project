package com.suzanelsamahy.vidviewer;

import android.app.Application;
import android.content.Context;

import com.suzanelsamahy.vidviewer.util.ConnectivityReciever;
import com.suzanelsamahy.vidviewer.util.LocaleHelper;


/**
 * Created by suzanelsamahy on 3/26/18.
 */

public class ChannelApplication extends Application {

    private static ChannelApplication mInstance;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ChannelApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReciever.ConnectivityReceiverListener listener) {
        ConnectivityReciever.connectivityReceiverListener = listener;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
