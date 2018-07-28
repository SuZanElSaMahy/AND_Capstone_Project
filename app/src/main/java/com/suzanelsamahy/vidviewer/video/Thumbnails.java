package com.suzanelsamahy.vidviewer.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class Thumbnails implements Parcelable {

    @SerializedName("default")
    @Expose
    private Default _default;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("high")
    @Expose
    private High high;
    @SerializedName("standard")
    @Expose
    private Standard standard;
    @SerializedName("maxres")
    @Expose
    private Maxres maxres;

    public Default getDefault() {
        return _default;
    }

    public void setDefault(Default _default) {
        this._default = _default;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public Maxres getMaxres() {
        return maxres;
    }

    public void setMaxres(Maxres maxres) {
        this.maxres = maxres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this._default, flags);
        dest.writeParcelable(this.medium, flags);
        dest.writeParcelable(this.high, flags);
        dest.writeParcelable(this.standard, flags);
        dest.writeParcelable(this.maxres, flags);
    }

    public Thumbnails() {
    }

    protected Thumbnails(Parcel in) {
        this._default = in.readParcelable(Default.class.getClassLoader());
        this.medium = in.readParcelable(Medium.class.getClassLoader());
        this.high = in.readParcelable(High.class.getClassLoader());
        this.standard = in.readParcelable(Standard.class.getClassLoader());
        this.maxres = in.readParcelable(Maxres.class.getClassLoader());
    }

    public static final Parcelable.Creator<Thumbnails> CREATOR = new Parcelable.Creator<Thumbnails>() {
        @Override
        public Thumbnails createFromParcel(Parcel source) {
            return new Thumbnails(source);
        }

        @Override
        public Thumbnails[] newArray(int size) {
            return new Thumbnails[size];
        }
    };
}
