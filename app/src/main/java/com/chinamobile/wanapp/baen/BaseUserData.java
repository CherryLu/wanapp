package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseUserData extends BaseBean{

    //用户信息
    @SerializedName("userData")
    private List<UserBean> userBeans;

    public UserBean getUserBeans() {
        if (userBeans!=null&&userBeans.size()>0){

            return userBeans.get(0);
        }
        return null;
    }

}
