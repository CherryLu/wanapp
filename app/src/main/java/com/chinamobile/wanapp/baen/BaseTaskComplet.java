package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 95470 on 2018/9/11.
 */

public class BaseTaskComplet extends BaseBean {


    @SerializedName("userData")
    private List<TaskComplet> taskComplets;


    public List<TaskComplet> getTaskComplets() {
        return taskComplets;
    }
}
