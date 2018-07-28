package com.suzanelsamahy.vidviewer.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class Localized implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
    }

    public Localized() {
    }

    protected Localized(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Localized> CREATOR = new Parcelable.Creator<Localized>() {
        @Override
        public Localized createFromParcel(Parcel source) {
            return new Localized(source);
        }

        @Override
        public Localized[] newArray(int size) {
            return new Localized[size];
        }
    };
}
