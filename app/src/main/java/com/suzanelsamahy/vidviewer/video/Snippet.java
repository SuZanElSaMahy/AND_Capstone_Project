package com.suzanelsamahy.vidviewer.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.suzanelsamahy.vidviewer.playlists.ResourceId;

import java.util.List;

/**
 * Created by suzanelsamahy on 3/15/18.
 */

public class Snippet implements Parcelable {

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("channelId")
    @Expose
    private String channelId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("liveBroadcastContent")
    @Expose
    private String liveBroadcastContent;
    @SerializedName("localized")
    @Expose
    private Localized localized;

    @SerializedName("playlistId")
    @Expose
    private String playlistId;
    @SerializedName("position")
    @Expose
    private Integer position;

    public ResourceId getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    @SerializedName("resourceId")
    @Expose
    private ResourceId resourceId;


    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

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

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    public Localized getLocalized() {
        return localized;
    }

    public void setLocalized(Localized localized) {
        this.localized = localized;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.publishedAt);
        dest.writeString(this.channelId);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.thumbnails, flags);
        dest.writeString(this.channelTitle);
        dest.writeStringList(this.tags);
        dest.writeString(this.categoryId);
        dest.writeString(this.liveBroadcastContent);
        dest.writeParcelable(this.localized, flags);
        dest.writeString(this.playlistId);
        dest.writeValue(this.position);
        dest.writeParcelable(this.resourceId, flags);
    }

    public Snippet() {
    }

    protected Snippet(Parcel in) {
        this.publishedAt = in.readString();
        this.channelId = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.thumbnails = in.readParcelable(Thumbnails.class.getClassLoader());
        this.channelTitle = in.readString();
        this.tags = in.createStringArrayList();
        this.categoryId = in.readString();
        this.liveBroadcastContent = in.readString();
        this.localized = in.readParcelable(Localized.class.getClassLoader());
        this.playlistId = in.readString();
        this.position = (Integer) in.readValue(Integer.class.getClassLoader());
        this.resourceId = in.readParcelable(ResourceId.class.getClassLoader());
    }

    public static final Parcelable.Creator<Snippet> CREATOR = new Parcelable.Creator<Snippet>() {
        @Override
        public Snippet createFromParcel(Parcel source) {
            return new Snippet(source);
        }

        @Override
        public Snippet[] newArray(int size) {
            return new Snippet[size];
        }
    };
}
