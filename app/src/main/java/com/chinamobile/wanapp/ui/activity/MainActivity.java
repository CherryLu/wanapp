package com.chinamobile.wanapp.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseBean;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.fragment.HomeFragment;
import com.chinamobile.wanapp.ui.fragment.MineFragment;
import com.chinamobile.wanapp.ui.fragment.NewFindFragment;
import com.chinamobile.wanapp.utils.UserManager;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.root_bottom_home_tab_1)
    RadioButton rootBottomHomeTab1;
    @Bind(R.id.root_bottom_home_tab_2)
    RadioButton rootBottomHomeTab2;
    @Bind(R.id.root_bottom_home_tab_3)
    RadioButton rootBottomHomeTab3;
    @Bind(R.id.root_bottom_tab_layout)
    RadioGroup rootBottomTabLayout;
    @Bind(R.id.container)
    FrameLayout container;

    private HomeFragment homeFragment;
    private NewFindFragment findFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tabCheck(0);
        /*if (Build.VERSION.SDK_INT >= 23) {
            askPermission();
        } else {
            initData();
        }*/

    }



    private void initData(){
        getImei();

    }

    private void getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        Log.e("IMEI",imei);

        getData(imei);
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

    private void getData(String sno){
        ApiServiceManager.userRegistApp(sno, new HttpResponse() {
            @Override
            public void onNext(BaseBean baseItem) {
                if (baseItem!=null){
                    UserManager.getInstance().setUserBean(baseItem.getUserBeans());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    private void hideAll(FragmentTransaction transaction){
        if (homeFragment!=null){
            transaction.hide(homeFragment);
        }

        if (findFragment!=null){
            transaction.hide(findFragment);
        }

        if (mineFragment!=null){
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    private void tabCheck(int position){
        TextView tabText1 = (TextView) findViewById(R.id.root_bottom_home_tab_1);
        TextView tabText2 = (TextView) findViewById(R.id.root_bottom_home_tab_2);
        TextView tabText3 = (TextView) findViewById(R.id.root_bottom_home_tab_3);

        tabText1.setTextColor(getResources().getColor(R.color.bottom_tab_txt));
        tabText2.setTextColor(getResources().getColor(R.color.bottom_tab_txt));
        tabText3.setTextColor(getResources().getColor(R.color.bottom_tab_txt));


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAll(transaction);
        switch (position){
            case 0:
                tabText1.setTextColor(getResources().getColor(R.color.blue_color));
                if (homeFragment==null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.container,homeFragment,"home");
                }
                transaction.show(homeFragment);
                break;
            case 1:
                tabText2.setTextColor(getResources().getColor(R.color.blue_color));
                if (findFragment==null){
                    findFragment = new NewFindFragment();
                    transaction.add(R.id.container,findFragment,"find");
                }
                transaction.show(findFragment);
                break;
            case 2:
                tabText3.setTextColor(getResources().getColor(R.color.blue_color));
                if (mineFragment==null){
                    mineFragment = new MineFragment();
                    transaction.add(R.id.container,mineFragment,"mine");
                }
                transaction.show(mineFragment);
                break;
        }
        transaction.commit();
    }

    @OnClick({R.id.root_bottom_home_tab_1, R.id.root_bottom_home_tab_2, R.id.root_bottom_home_tab_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root_bottom_home_tab_1:
                tabCheck(0);
                break;
            case R.id.root_bottom_home_tab_2:
                tabCheck(1);
                break;
            case R.id.root_bottom_home_tab_3:
                tabCheck(2);
                break;
        }
    }

}
