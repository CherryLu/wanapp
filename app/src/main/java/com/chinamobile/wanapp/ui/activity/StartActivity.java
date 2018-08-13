package com.chinamobile.wanapp.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseBean;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.utils.GlideUtil;
import com.chinamobile.wanapp.utils.Nagivator;
import com.chinamobile.wanapp.utils.SharedPreferencesUtils;
import com.chinamobile.wanapp.utils.UserManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/9.
 */

public class StartActivity extends BaseActivity {


    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.ader_timer_txt)
    TextView aderTimerTxt;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:

                    boolean guide = (boolean) SharedPreferencesUtils.getParam(StartActivity.this, "GUIDE", false);
                    if (guide) {//首页
                        Nagivator.startMainActivity(StartActivity.this);
                    } else {//引导页

                        Nagivator.startGuideActivity(StartActivity.this);
                    }
                    finish();
                    break;

                case 300:
                    getOpenAPP(UserManager.getInstance().getId());
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            askPermission();
        } else {
            initData();
        }

        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

    }


    private void initData() {
        getImei();

    }

    private void getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();

        //handler.sendEmptyMessageDelayed(200,3000);
        // getData(imei);

        //getOpenAPP("10000055");
        getLsit();
    }

    CountDownTimer timer = new CountDownTimer(4000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            if (aderTimerTxt.getVisibility()== View.GONE){
                aderTimerTxt.setVisibility(View.VISIBLE);
            }
            aderTimerTxt.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
           if (handler!=null){
               handler.sendEmptyMessage(200);
           }
        }
    };

    private void getOpenAPP(String id) {
        ApiServiceManager.getAPPOpen(id, new HttpResponse() {
            @Override
            public void onNext(BaseBean baseItem) {
                if (baseItem != null) {
                    if (baseItem.getConfigData() != null && baseItem.getConfigData().getJas_res() != null && baseItem.getConfigData().getJas_res().size() > 0) {
                        GlideUtil.loadImageView(StartActivity.this, baseItem.getConfigData().getJas_res().get(0).getStartimgUrl(), image);
                        timer.start();
                    }

                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    private void getLsit(){
        ApiServiceManager.getDataList("0", new HttpResponse() {
            @Override
            public void onNext(BaseBean baseItem) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void getData(String sno) {
        ApiServiceManager.userRegistApp(sno, new HttpResponse() {
            @Override
            public void onNext(BaseBean baseItem) {
                if (baseItem != null) {
                    UserManager.getInstance().setUserBean(baseItem.getUserBeans());
                }

                handler.sendEmptyMessage(300);


            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermission() {
        ArrayList<String> permissions = new ArrayList<String>();
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
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


    @OnClick(R.id.ader_timer_txt)
    public void onClick() {
        timer.cancel();
        if (handler!=null){
            handler.sendEmptyMessage(200);
        }

    }
}
