package com.suzanelsamahy.vidviewer.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suzanelsamahy on 3/20/18.
 */

public class Maxres implements Parcelable {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeValue(this.width);
        dest.writeValue(this.height);
    }

    public Maxres() {
    }

    protected Maxres(Parcel in) {
        this.url = in.readString();
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Maxres> CREATOR = new Parcelable.Creator<Maxres>() {
        @Override
        public Maxres createFromParcel(Parcel source) {
            return new Maxres(source);
        }

        @Override
        public Maxres[] newArray(int size) {
            return new Maxres[size];
        }
    };
}