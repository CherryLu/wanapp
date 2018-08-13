package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/8/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersonData {

    @SerializedName("apkUrl")
    private String apkUrl;

    @SerializedName("miniVision")
    private String miniVision;

    @SerializedName("remark")
    private String remark;


    @SerializedName("visionId")
    private String visionId;

    @SerializedName("startimgUrl")
    private String startimgUrl;


    public String getApkUrl() {
        return apkUrl;
    }

    public String getMiniVision() {
        return miniVision;
    }

    public String getRemark() {
        return remark;
    }

    public String getVisionId() {
        return visionId;
    }

    public String getStartimgUrl() {
        return startimgUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public void setMiniVision(String miniVision) {
        this.miniVision = miniVision;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setVisionId(String visionId) {
        this.visionId = visionId;
    }

    public void setStartimgUrl(String startimgUrl) {
        this.startimgUrl = startimgUrl;
    }
}
