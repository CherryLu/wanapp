package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/8/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigData implements Serializable {

    @SerializedName("ifnew")
    private int ifnew;

    @SerializedName("vu_res")
    private List<VersonData> vu_res;

    @SerializedName("jas_res")
    private List<VersonData> jas_res;


    public int getIfnew() {
        return ifnew;
    }

    public List<VersonData> getVu_res() {
        return vu_res;
    }

    public List<VersonData> getJas_res() {
        return jas_res;
    }
}
