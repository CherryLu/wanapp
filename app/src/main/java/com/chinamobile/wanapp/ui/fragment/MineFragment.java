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
    @Bind(R.id.login_txt)
    TextView loginTxt;
    @Bind(R.id.login_area)
    RelativeLayout loginArea;
    @Bind(R.id.user_level)
    TextView userLevel;
    @Bind(R.id.level_add)
    TextView levelAdd;
    @Bind(R.id.txt3)
    TextView txt3;
    @Bind(R.id.get)
    ImageView get;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.header_image, R.id.login_txt, R.id.login_area, R.id.user_level, R.id.level_add, R.id.txt3, R.id.get, R.id.renwujilu, R.id.wodeshouyi, R.id.yonghufankui, R.id.guanyuwomen, R.id.xinshoubangzhu, R.id.xiugaimima, R.id.login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_image:
                break;
            case R.id.login_txt:
                break;
            case R.id.login_area:
                break;
            case R.id.user_level:
                break;
            case R.id.level_add:
                break;
            case R.id.txt3:
                break;
            case R.id.get:
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
        }
    }
}
