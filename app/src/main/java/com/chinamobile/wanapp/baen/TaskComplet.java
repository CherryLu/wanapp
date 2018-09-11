package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 95470 on 2018/9/11.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskComplet {

    @SerializedName("approveTime")
    private long approveTime;

    @SerializedName("eid")
    private String eid;


    @SerializedName("id")
    private String id;


    @SerializedName("jid")
    private String jid;


    @SerializedName("jzGain")
    private String jzGain;


    @SerializedName("remark")
    private String remark;


    @SerializedName("status")
    private String status;


    @SerializedName("submitTime")
    private String submitTime;


    @SerializedName("uid")
    private String uid;


    private int weekday;


    public int getWeekday() {

        Date date = new Date(approveTime);
        //Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        weekday = cal.get(Calendar.DAY_OF_WEEK) -1;
        return weekday;
    }

    public long getApproveTime() {
        return approveTime;
    }

    public String getEid() {
        return eid;
    }

    public String getId() {
        return id;
    }

    public String getJid() {
        return jid;
    }

    public String getJzGain() {
        return jzGain;
    }

    public String getRemark() {
        return remark;
    }

    public String getStatus() {
        return status;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public String getUid() {
        return uid;
    }
}
