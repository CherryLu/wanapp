package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBean implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("sno")
    private String sno;

    private String ifnew;

    public String getIfnew() {
        return ifnew;
    }

    public void setIfnew(String ifnew) {
        this.ifnew = ifnew;
    }

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
