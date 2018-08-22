package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22.
 */

public class BaseWelfare extends BaseBean {


    @SerializedName("userData")
    private List<Welfare> welfares;


    public List<Welfare> getWelfares() {
        return welfares;
    }
}
