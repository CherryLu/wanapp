package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeBean implements Serializable {

    @SerializedName("Janm_res")
    private List<TaskData> Janm_res;//公告信息

    @SerializedName("Jadm_res")
    private List<TaskData> Jadm_res;//首页弹窗

    @SerializedName("mbm_res")
    private List<TaskData> mbm_res;//用户信息  余额之类

    @SerializedName("Jjp_res")
    private List<TaskData> Jjp_res;//任务列表

    @SerializedName("Jabm_res")
    private List<TaskData> Jabm_res;//banner

    @SerializedName("Jamm_res")
    private List<TaskData> Jamm_res;//分类标题


    public List<TaskData> getJabm_res() {
        return Jabm_res;
    }

    public List<TaskData> getJamm_res() {
        return Jamm_res;
    }

    public List<TaskData> getJanm_res() {

        return Janm_res;
    }

    public List<TaskData> getJadm_res() {
        return Jadm_res;
    }

    public List<TaskData> getMbm_res() {
        return mbm_res;
    }

    public List<TaskData> getJjp_res() {
        return Jjp_res;
    }
}
