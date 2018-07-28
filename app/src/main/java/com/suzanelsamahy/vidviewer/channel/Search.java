package com.suzanelsamahy.vidviewer.channel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.suzanelsamahy.vidviewer.video.Snippet;


/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class Search implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("id")
    @Expose
    private IdDetails id;
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

    public IdDetails getId() {
        return id;
    }

    public void setId(IdDetails id) {
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
        dest.writeParcelable(this.id, flags);
        dest.writeParcelable(this.snippet, flags);
    }

    public Search() {
    }

    protected Search(Parcel in) {
        this.kind = in.readString();
        this.etag = in.readString();
        this.id = in.readParcelable(IdDetails.class.getClassLoader());
        this.snippet = in.readParcelable(Snippet.class.getClassLoader());
    }

    public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel source) {
            return new Search(source);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };
}
