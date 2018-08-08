package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.utils.Nagivator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/1.
 */

public class TaskDetailsActivity extends BaseActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.app_pic)
    ImageView appPic;
    @Bind(R.id.app_name)
    TextView appName;
    @Bind(R.id.app_size)
    TextView appSize;
    @Bind(R.id.app_lable1)
    TextView appLable1;
    @Bind(R.id.app_lable2)
    TextView appLable2;
    @Bind(R.id.steps_layouy)
    LinearLayout stepsLayouy;
    @Bind(R.id.pic1)
    ImageView pic1;
    @Bind(R.id.pic2)
    ImageView pic2;
    @Bind(R.id.image_layout)
    LinearLayout imageLayout;
    @Bind(R.id.download)
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_download);
        ButterKnife.bind(this);
        setTitleBar("任务详情");
        initView();
    }


    private void initView(){
        stepsLayouy.removeAllViews();
        for (int i=0;i<3;i++){
            stepsLayouy.addView(getStepView(i));
        }
    }


    private void initLayout(){

    }

    @OnClick({R.id.back_image, R.id.download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.download:
                break;
        }
    }


    private View getStepView(int positin){

        View view  = View.inflate(this,R.layout.step_item,null);
        TextView step_number = view.findViewById(R.id.step_number);
        View line = view.findViewById(R.id.line);
        TextView step_descrip = view.findViewById(R.id.step_descrip);
        TextView step_descripe = view.findViewById(R.id.step_descripe);
        TextView step_money = view.findViewById(R.id.step_money);
        TextView step_completion = view.findViewById(R.id.step_completion);
        step_number.setText(positin+"");
        return view;
    }
}
