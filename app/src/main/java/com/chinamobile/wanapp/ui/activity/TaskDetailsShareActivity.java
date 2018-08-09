package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.ui.view.TipsDialog;
import com.chinamobile.wanapp.utils.Nagivator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailsShareActivity extends BaseActivity {


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
    @Bind(R.id.app_time)
    TextView appTime;
    @Bind(R.id.image_layout)
    LinearLayout imageLayout;
    @Bind(R.id.tips)
    TextView tips;

    TipsDialog tipsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_share);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_image, R.id.tips})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.tips:

                if (tipsDialog==null){
                    tipsDialog = new TipsDialog(this);
                }

                if (!tipsDialog.isShowing()){
                    tipsDialog.show();
                }

                break;
        }
    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.bind(this);
    }
}
