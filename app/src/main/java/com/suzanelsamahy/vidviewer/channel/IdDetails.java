package com.suzanelsamahy.vidviewer.channel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class IdDetails implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("videoId")
    @Expose
    private String videoId;
    @SerializedName("playlistId")
    @Expose
    private String playlistId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.videoId);
        dest.writeString(this.playlistId);
    }

    public IdDetails() {
    }

    protected IdDetails(Parcel in) {
        this.kind = in.readString();
        this.videoId = in.readString();
        this.playlistId = in.readString();
    }

    public static final Creator<IdDetails> CREATOR = new Creator<IdDetails>() {
        @Override
        public IdDetails createFromParcel(Parcel source) {
            return new IdDetails(source);
        }

        @Override
        public IdDetails[] newArray(int size) {
            return new IdDetails[size];
        }
    };
}
