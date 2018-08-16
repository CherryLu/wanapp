package com.chinamobile.wanapp.baen;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class SignData {

    @SerializedName("remark")
    private String remark;

    @SerializedName("signTime")
    private String signTime;

    @SerializedName("uid")
    private String uid;


    public String getRemark() {
        return remark;
    }

    public String getSignTime() {
        return signTime;
    }

    public String getUid() {
        return uid;
    }


    public int getData(){
         int i = -1;
         if (TextUtils.isEmpty(signTime)){
            return i;
         }
          long time = Long.parseLong(signTime);
          Date date = new Date(time);
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(date);
          int d = calendar.get(Calendar.DAY_OF_WEEK);
          if (d==1){
            i =7;
          }else {
            i =d-1;
            }
        return i;

    }
}
