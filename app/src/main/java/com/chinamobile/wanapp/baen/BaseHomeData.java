package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

public class BaseHomeData extends BaseBean {


    //首页信息
    @SerializedName("userData")
    private HomeBean homeBean;


    public HomeBean getHomeBean() {
        return homeBean;
    }

}
