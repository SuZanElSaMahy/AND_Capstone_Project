package com.suzanelsamahy.vidviewer.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class Status {


    @SerializedName("uploadStatus")
    @Expose
    private String uploadStatus;
    @SerializedName("privacyStatus")
    @Expose
    private String privacyStatus;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("embeddable")
    @Expose
    private Boolean embeddable;
    @SerializedName("publicStatsViewable")
    @Expose
    private Boolean publicStatsViewable;

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getPrivacyStatus() {
        return privacyStatus;
    }

    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

    public Boolean getPublicStatsViewable() {
        return publicStatsViewable;
    }

    public void setPublicStatsViewable(Boolean publicStatsViewable) {
        this.publicStatsViewable = publicStatsViewable;
    }


}
