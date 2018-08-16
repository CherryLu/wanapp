package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignBean {


    @SerializedName("Jusn_res")
    private List<SignData> signData;


    public List<SignData> getSignData() {
        return signData;
    }
}
