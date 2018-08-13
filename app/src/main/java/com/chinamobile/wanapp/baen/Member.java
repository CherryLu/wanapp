package com.chinamobile.wanapp.baen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member implements Serializable {

    @SerializedName("mobile")
    private String mobile;


    public String getMobile() {
        return mobile;
    }
}
