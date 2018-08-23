package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/8/23.
 */

public class UserData {
    //用户信息
    @SerializedName("mem")
    private List<UserBean> userBeans;

    @SerializedName("ifnew")
    private String ifnew;

    public List<UserBean> getUserBeans() {
        return userBeans;
    }

    public String getIfnew() {
        return ifnew;
    }
}
