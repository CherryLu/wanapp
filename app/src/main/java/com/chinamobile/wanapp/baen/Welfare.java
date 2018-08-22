package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/22.
 */

public class Welfare implements Serializable {

    @SerializedName("eid")
    private String eid;

    @SerializedName("id")
    private String id;

    @SerializedName("remark")
    private String remark;

    @SerializedName("request")
    private String request;

    @SerializedName("ruleName")
    private String ruleName;

    @SerializedName("sid")
    private String sid;

    @SerializedName("status")
    private String status;


    public String getEid() {
        return eid;
    }

    public String getId() {
        return id;
    }

    public String getRemark() {
        return remark;
    }

    public String getRequest() {
        return request;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getSid() {
        return sid;
    }

    public String getStatus() {
        return status;
    }
}
