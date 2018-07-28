package com.suzanelsamahy.vidviewer.playlists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.suzanelsamahy.vidviewer.video.PageInfo;

import java.util.List;

/**
 * Created by suzanelsamahy on 3/22/18.
 */

public class PlayListResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;
    @SerializedName("items")
    @Expose
    private List<PlayList> items = null;

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

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<PlayList> getItems() {
        return items;
    }

    public void setItems(List<PlayList> items) {
        this.items = items;
    }

}


