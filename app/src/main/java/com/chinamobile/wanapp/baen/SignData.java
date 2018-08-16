package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

public class SignData {

    @SerializedName("remark")
    private String remark;

    @SerializedName("signTime")
    private String signTime;

    @SerializedName("uid")
    private String uid;


    public String getRemark() {
        return remark;
    }

    public String getSignTime() {
        return signTime;
    }

    public String getUid() {
        return uid;
    }
}
