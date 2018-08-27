package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/22.
 */

public class Welfare implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("remark")
    private String remark;

    @SerializedName("request")
    private int request;

    @SerializedName("ruleName")
    private String ruleName;

    @SerializedName("sid")
    private String sid;

    @SerializedName("status")
    private String status;

    @SerializedName("jujDc")
    private int jujDc;

    @SerializedName("jujEid")
    private String jujEid;

    @SerializedName("eid")
    private String eid;


    public int getJujDc() {
        return jujDc;
    }

    public String getJujEid() {
        return jujEid;
    }

    public String getEid() {
        return eid;
    }

    public String getId() {
        return id;
    }

    public String getRemark() {
        return remark;
    }

    public int getRequest() {
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
