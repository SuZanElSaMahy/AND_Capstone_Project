package com.suzanelsamahy.vidviewer.playlists;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.suzanelsamahy.vidviewer.video.Snippet;


/**
 * Created by suzanelsamahy on 3/22/18.
 */

public class PlayList implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("snippet")
    @Expose
    private Snippet snippet;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.etag);
        dest.writeString(this.id);
        dest.writeParcelable(this.snippet, flags);
    }

    public PlayList() {
    }

    protected PlayList(Parcel in) {
        this.kind = in.readString();
        this.etag = in.readString();
        this.id = in.readString();
        this.snippet = in.readParcelable(Snippet.class.getClassLoader());
    }

    public static final Parcelable.Creator<PlayList> CREATOR = new Parcelable.Creator<PlayList>() {
        @Override
        public PlayList createFromParcel(Parcel source) {
            return new PlayList(source);
        }

        @Override
        public PlayList[] newArray(int size) {
            return new PlayList[size];
        }
    };
}
