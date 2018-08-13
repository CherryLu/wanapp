package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseBean implements Serializable {

    @SerializedName("code")
    private int code;


    @SerializedName("msg")
    private String msg;



    //用户信息
    //@SerializedName("userData")
    private List<UserBean> userBeans;

    //首页信息
    //@SerializedName("userData")
    private HomeBean homeBean;


    @SerializedName("userData")
    private ConfigData configData;

    public ConfigData getConfigData() {
        return configData;
    }

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


    public HomeBean getHomeBean() {
        return homeBean;
    }
}
