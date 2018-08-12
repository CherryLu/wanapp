package com.chinamobile.wanapp.baen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    @SerializedName("mobile")
    private String mobile;


    public String getMobile() {
        return mobile;
    }
}
