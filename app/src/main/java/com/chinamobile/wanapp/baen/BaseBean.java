package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/8/6.
 */

public class BaseBean {

    @SerializedName("code")
    private int code;


    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
