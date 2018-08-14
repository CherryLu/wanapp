package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/8/14.
 */

public class BaseTaskList extends BaseBean {

    @SerializedName("userData")
    private List<TaskData> taskDatas;


    public List<TaskData> getTaskDatas() {
        return taskDatas;
    }


}
