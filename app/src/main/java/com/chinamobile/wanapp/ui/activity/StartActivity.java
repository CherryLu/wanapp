package com.chinamobile.wanapp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.chinamobile.wanapp.utils.Nagivator;
import com.chinamobile.wanapp.utils.SharedPreferencesUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/9.
 */

public class StartActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            askPermission();
        } else {
            initData();
        }

        boolean guide = (boolean) SharedPreferencesUtils.getParam(this, "GUIDE", false);
        if (guide) {//首页
            Nagivator.startMainActivity(this);
        } else {//引导页
            Nagivator.startGuideActivity(this);
        }

    }


    private void initData(){
        getImei();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermission() {
        ArrayList<String> permissions = new ArrayList<String>();
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if (permissions != null && permissions.size() > 0) {
            String[] st = new String[permissions.size()];
            for (int i = 0; i < permissions.size(); i++) {
                st[i] = permissions.get(i);
            }
            requestPermissions(st, 1);
        } else {//初始化
            initData();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults != null && grantResults.length > 0) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    this.finish();
                    return;
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        initData();
    }


    private void getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission")
        String imei = telephonyManager.getDeviceId();
        Log.e("IMEI",imei);
    }


}
