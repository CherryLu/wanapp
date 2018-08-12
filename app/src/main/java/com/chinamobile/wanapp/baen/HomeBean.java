package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeBean {

    @SerializedName("Janm_res")
    private List<TaskData> Janm_res;

    @SerializedName("Jadm_res")
    private List<TaskData> Jadm_res;

    @SerializedName("mbm_res")
    private List<TaskData> mbm_res;

    @SerializedName("Jjp_res")
    private List<TaskData> Jjp_res;

    @SerializedName("Jabm_res")
    private List<TaskData> Jabm_res;

    @SerializedName("Jamm_res")
    private List<TaskData> Jamm_res;


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
