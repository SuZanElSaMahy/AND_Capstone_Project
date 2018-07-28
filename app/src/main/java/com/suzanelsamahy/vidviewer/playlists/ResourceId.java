package com.suzanelsamahy.vidviewer.playlists;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public class ResourceId implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("videoId")
    @Expose
    private String videoId;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.videoId);
    }

    public ResourceId() {
    }

    protected ResourceId(Parcel in) {
        this.kind = in.readString();
        this.videoId = in.readString();
    }

    public static final Parcelable.Creator<ResourceId> CREATOR = new Parcelable.Creator<ResourceId>() {
        @Override
        public ResourceId createFromParcel(Parcel source) {
            return new ResourceId(source);
        }

        @Override
        public ResourceId[] newArray(int size) {
            return new ResourceId[size];
        }
    };
}
