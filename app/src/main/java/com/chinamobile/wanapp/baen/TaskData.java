package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskData {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("action")
    private String action;

    @SerializedName("imgUrl")
    private String imgUrl;

    @SerializedName("param")
    private String param;

    @SerializedName("doneCount")
    private int doneCount;


    @SerializedName("iconUrl")
    private String iconUrl;

    @SerializedName("jobTags")
    private String jobTags;

    @SerializedName("jzGain")
    private int  jzGain;

    @SerializedName("limiteCount")
    private int limiteCount;

    @SerializedName("mid")
    private int mid;

    @SerializedName("sort")
    private int sort;


    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("mname")
    private String mname;

    @SerializedName("member")
    private Member member;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAction() {
        return action;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getParam() {
        return param;
    }

    public int getDoneCount() {
        return doneCount;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getJobTags() {
        return jobTags;
    }

    public int getJzGain() {
        return jzGain;
    }

    public int getLimiteCount() {
        return limiteCount;
    }

    public int getMid() {
        return mid;
    }

    public int getSort() {
        return sort;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getMname() {
        return mname;
    }


    public Member getMember() {
        return member;
    }
}
