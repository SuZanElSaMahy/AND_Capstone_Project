package com.suzanelsamahy.vidviewer.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.suzanelsamahy.vidviewer.EnterChannelIdActivity;
import com.suzanelsamahy.vidviewer.MainActivity;
import com.suzanelsamahy.vidviewer.R;
import com.suzanelsamahy.vidviewer.util.SharedPreferencesManager;

/**
 * Implementation of App Widget functionality.
 */
public class ChannelWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId , String name) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.channel_widget);
        views.setTextViewText(R.id.appwidget_text, name);


        if(chName!=null && !chName.isEmpty()){
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widget_view, pendingIntent);
        } else {
            Intent intent = new Intent(context, EnterChannelIdActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widget_view, pendingIntent);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
       String channelName =  SharedPreferencesManager.getInstance(context).getStringPref(SharedPreferencesManager.CHANNEL_NAME);

        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId,channelName);
            Toast.makeText(context, "Widget has been updated! ", Toast.LENGTH_SHORT).show();
        }
    }

    private static String chName;
    public static void getChannelNameForWidget(String name) {
        chName = name;
    }


    @Override
    public void onEnabled(Context context) {
        ChannelWidgetService.startChannelWidget(context);
        super.onEnabled(context);
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.channel_widget);
        views.setTextViewText(R.id.appwidget_text, chName);
    }

}

