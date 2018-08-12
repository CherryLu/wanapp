package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseBean {

    @SerializedName("code")
    private int code;


    @SerializedName("msg")
    private String msg;

    @SerializedName("userData")
    private List<UserBean> userBeans;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public UserBean getUserBeans() {
        if (userBeans!=null&&userBeans.size()>0){

           return userBeans.get(0);
        }
        return null;
    }
}
