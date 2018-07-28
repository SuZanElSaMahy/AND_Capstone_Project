package com.suzanelsamahy.vidviewer.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.suzanelsamahy.vidviewer.util.SharedPreferencesManager;


public class ChannelWidgetService extends IntentService {

    private String channelName;
    public ChannelWidgetService() {
        super("ChannelWidgetService");
    }

    public static void startChannelWidget(Context context) {
        Intent intent = new Intent(context, ChannelWidgetService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
          channelName =  SharedPreferencesManager.getInstance(this).getStringPref(SharedPreferencesManager.CHANNEL_NAME);
          ChannelWidget.getChannelNameForWidget(channelName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
