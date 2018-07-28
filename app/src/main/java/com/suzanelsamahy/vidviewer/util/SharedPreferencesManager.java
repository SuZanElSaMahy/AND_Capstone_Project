package com.suzanelsamahy.vidviewer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesManager {
    public static final String CHANNEL_ID = "channel_id";
    public static final String CHANNEL_NAME = "channel_name";
    private static SharedPreferencesManager ourInstance;
    private SharedPreferences sharedPreferences;

    public static SharedPreferencesManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SharedPreferencesManager(context);
        }
        return ourInstance;
    }

    private SharedPreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void saveStringPref(String key,String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getStringPref(String key){
        return sharedPreferences.getString(key, "");
    }


    public void removeKey(String key) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.remove(key).apply();
    }
}
