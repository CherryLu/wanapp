package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShareData {


    @SerializedName("shareImage")
    private String shareImage;


    @SerializedName("shareUrl")
    private String shareUrl;


    @SerializedName("shareTxt")
    private String shareTxt;


    public String getShareImage() {
        return shareImage;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public String getShareTxt() {
        return shareTxt;
    }
}
