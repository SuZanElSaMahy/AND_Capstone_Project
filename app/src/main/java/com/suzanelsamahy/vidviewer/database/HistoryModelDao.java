package com.suzanelsamahy.vidviewer.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface HistoryModelDao {

    @Query("select * from HistoryModel")
    LiveData<List<HistoryModel>> getAllWatchedItems();

    @Query("select * from HistoryModel where video_id = :id")
    HistoryModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addWatchedVideo(HistoryModel borrowModel);

    @Delete
    void deleteWatchedVideo(HistoryModel borrowModel);

}
