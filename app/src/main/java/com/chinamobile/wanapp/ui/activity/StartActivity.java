package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.chinamobile.wanapp.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2018/8/9.
 */

public class StartActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean guide = (boolean) SharedPreferencesUtils.getParam(this,"GUIDE",false);
        if (guide){//首页

        }else {//引导页

        }
        //getImei();
    }


    private void getImei(){
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        Log.e("IMEI",imei);
    }


}
