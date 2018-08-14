package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseBean implements Serializable {

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
