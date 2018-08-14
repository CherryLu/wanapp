package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

public class BaseConfigData extends BaseBean {

    @SerializedName("userData")
    private ConfigData configData;

    public ConfigData getConfigData() {
        return configData;
    }


}
