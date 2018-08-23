package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseUserData extends BaseBean{

    //用户信息
    @SerializedName("userData")
    private UserData userData;

    public UserBean getUserBeans() {
        if (userData!=null&&userData.getUserBeans()!=null&&userData.getUserBeans().size()>0){
            userData.getUserBeans().get(0).setIfnew(userData.getIfnew());
            return userData.getUserBeans().get(0);
        }
        return null;
    }

}
