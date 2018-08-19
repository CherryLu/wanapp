package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/29.
 */

public class MineFragment extends BaseFragment {

    @Bind(R.id.header_image)
    ImageView headerImage;
    @Bind(R.id.login_area)
    RelativeLayout loginArea;
    @Bind(R.id.renwujilu)
    LinearLayout renwujilu;
    @Bind(R.id.wodeshouyi)
    LinearLayout wodeshouyi;
    @Bind(R.id.yonghufankui)
    LinearLayout yonghufankui;
    @Bind(R.id.guanyuwomen)
    LinearLayout guanyuwomen;
    @Bind(R.id.xinshoubangzhu)
    LinearLayout xinshoubangzhu;
    @Bind(R.id.xiugaimima)
    LinearLayout xiugaimima;
    @Bind(R.id.login_out)
    Button loginOut;
    @Bind(R.id.request_code)
    TextView requestCode;
    @Bind(R.id.request_all)
    TextView requestAll;
    @Bind(R.id.message_btn)
    TextView messageBtn;
    @Bind(R.id.login_name)
    TextView loginName;
    @Bind(R.id.login_code)
    TextView loginCode;
    @Bind(R.id.login_lv)
    TextView loginLv;
    @Bind(R.id.login_lv_up)
    TextView loginLvUp;
    @Bind(R.id.login_quanyi)
    TextView loginQuanyi;
    @Bind(R.id.login_yaoqing)
    TextView loginYaoqing;
    @Bind(R.id.icon_pic1)
    ImageView iconPic1;
    @Bind(R.id.icon_txt1)
    TextView iconTxt1;
    @Bind(R.id.layout1)
    RelativeLayout layout1;
    @Bind(R.id.icon_pic2)
    ImageView iconPic2;
    @Bind(R.id.icon_txt2)
    TextView iconTxt2;
    @Bind(R.id.layout2)
    RelativeLayout layout2;
    @Bind(R.id.icon_pic3)
    ImageView iconPic3;
    @Bind(R.id.icon_txt3)
    TextView iconTxt3;
    @Bind(R.id.layout3)
    RelativeLayout layout3;
    @Bind(R.id.icon_pic4)
    ImageView iconPic4;
    @Bind(R.id.icon_txt4)
    TextView iconTxt4;
    @Bind(R.id.layout4)
    RelativeLayout layout4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.header_image, R.id.login_area, R.id.renwujilu, R.id.wodeshouyi, R.id.yonghufankui, R.id.guanyuwomen, R.id.xinshoubangzhu, R.id.xiugaimima, R.id.login_out,R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_image:
                break;
            case R.id.renwujilu:
                break;
            case R.id.wodeshouyi:
                break;
            case R.id.yonghufankui:
                break;
            case R.id.guanyuwomen:
                break;
            case R.id.xinshoubangzhu:
                break;
            case R.id.xiugaimima:
                break;
            case R.id.login_out:
                break;
            case R.id.layout1:
                break;
            case R.id.layout2:
                break;
            case R.id.layout3:
                break;
            case R.id.layout4:
                break;
        }
    }

}
