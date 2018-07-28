package com.suzanelsamahy.vidviewer.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class HistoryModel implements Parcelable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String video_id;

    private String thumbnail_url;
    private String video_title;
    private String video_desc;


    public HistoryModel(String video_id, String thumbnail_url, String video_title, String video_desc) {
        this.video_id = video_id;
        this.thumbnail_url = thumbnail_url;
        this.video_title = video_title;
        this.video_desc = video_desc;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_desc() {
        return video_desc;
    }

    public void setVideo_desc(String video_desc) {
        this.video_desc = video_desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.video_id);
        dest.writeString(this.thumbnail_url);
        dest.writeString(this.video_title);
        dest.writeString(this.video_desc);
    }

    protected HistoryModel(Parcel in) {
        this.video_id = in.readString();
        this.thumbnail_url = in.readString();
        this.video_title = in.readString();
        this.video_desc = in.readString();
    }

    public static final Parcelable.Creator<HistoryModel> CREATOR = new Parcelable.Creator<HistoryModel>() {
        @Override
        public HistoryModel createFromParcel(Parcel source) {
            return new HistoryModel(source);
        }

        @Override
        public HistoryModel[] newArray(int size) {
            return new HistoryModel[size];
        }
    };
}
