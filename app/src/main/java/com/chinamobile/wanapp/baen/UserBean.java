package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBean {

    @SerializedName("id")
    private String id;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("sno")
    private String sno;


    public String getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSno() {
        return sno;
    }
}
