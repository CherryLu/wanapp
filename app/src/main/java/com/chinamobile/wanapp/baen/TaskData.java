package com.chinamobile.wanapp.baen;

import com.chinamobile.wanapp.utils.LogUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskData implements Serializable {

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
    private double  jzGain = 0d;

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

    @SerializedName("pid")
    private String pid;

    @SerializedName("fid")
    private String fid;


    @SerializedName("jobStr")
    private String jobStr;

    @SerializedName("notice")
    private String notice;

    @SerializedName("rules")
    private String rules;

    @SerializedName("sampleimgUrl")
    private String sampleimgUrl;


    @SerializedName("eid")
    private String eid;





    private int type; //视图类型 0

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


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

    public double getJzGain() {
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

    public String getPid() {
        return pid;
    }


    public String getFid() {
        return fid;
    }

    public JobStr getJobStr() {
        Gson gson = new Gson();
        //jobStr = jobStr.substring(1,jobStr.length()-1);
        LogUtils.e("ZX",jobStr);
        JobStr jobs = gson.fromJson(jobStr,JobStr.class);
        return jobs;
    }

    public String getNotice() {
        return notice;
    }

    public String getRules() {
        return rules;
    }

    public String getSampleimgUrl() {
        return sampleimgUrl;
    }

    public String getEid() {
        return eid;
    }
}
