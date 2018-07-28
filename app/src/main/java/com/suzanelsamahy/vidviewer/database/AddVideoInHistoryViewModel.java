package com.suzanelsamahy.vidviewer.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;


public class AddVideoInHistoryViewModel extends AndroidViewModel {

    private HistoryDatabase appDatabase;

    public AddVideoInHistoryViewModel(Application application) {
        super(application);
        appDatabase = HistoryDatabase.getDatabase(this.getApplication());
    }

    public void addVideoToDB(final HistoryModel borrowModel) {
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<HistoryModel, Void, Void> {

        private HistoryDatabase db;

        addAsyncTask(HistoryDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final HistoryModel... params) {
            db.itemAndPersonModel().addWatchedVideo(params[0]);
            return null;
        }
    }
}
