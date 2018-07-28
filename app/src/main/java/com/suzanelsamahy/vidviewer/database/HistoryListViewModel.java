package com.suzanelsamahy.vidviewer.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class HistoryListViewModel extends AndroidViewModel {


    private final LiveData<List<HistoryModel>> itemAndPersonList;
    private HistoryDatabase appDatabase;


    public HistoryListViewModel(@NonNull Application application) {
        super(application);
        appDatabase = HistoryDatabase.getDatabase(this.getApplication());
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllWatchedItems();
    }


    public LiveData<List<HistoryModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(HistoryModel borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<HistoryModel, Void, Void> {

        private HistoryDatabase db;

        deleteAsyncTask(HistoryDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final HistoryModel... params) {
            db.itemAndPersonModel().deleteWatchedVideo(params[0]);
            return null;
        }

    }

}
