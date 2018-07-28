package com.suzanelsamahy.vidviewer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {HistoryModel.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    private static HistoryDatabase INSTANCE;

    public static HistoryDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HistoryDatabase.class, "history_db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract HistoryModelDao itemAndPersonModel();



//    @Override
//    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
//        return null;
//    }
//
//    @Override
//    protected InvalidationTracker createInvalidationTracker() {
//        return null;
//    }


}
