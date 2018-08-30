package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeeksPlanActivity extends BaseActivity {


    @Bind(R.id.layout_1_pic)
    ImageView layout1Pic;
    @Bind(R.id.layout_1_btn)
    TextView layout1Btn;
    @Bind(R.id.layout_1)
    LinearLayout layout1;
    @Bind(R.id.layout_2_pic)
    ImageView layout2Pic;
    @Bind(R.id.layout_2_btn)
    TextView layout2Btn;
    @Bind(R.id.layout_2)
    LinearLayout layout2;
    @Bind(R.id.layout_3_pic)
    ImageView layout3Pic;
    @Bind(R.id.layout_3_btn)
    TextView layout3Btn;
    @Bind(R.id.layout_3)
    LinearLayout layout3;
    @Bind(R.id.layout_4_pic)
    ImageView layout4Pic;
    @Bind(R.id.layout_4_btn)
    TextView layout4Btn;
    @Bind(R.id.layout_4)
    LinearLayout layout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everday);
        ButterKnife.bind(this);

    }


    private void getData(){
        //ApiServiceManager.
    }

    @OnClick({R.id.layout_1_pic, R.id.layout_1, R.id.layout_2, R.id.layout_3, R.id.layout_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_1_pic:
                break;
            case R.id.layout_1:
                break;
            case R.id.layout_2:
                break;
            case R.id.layout_3:
                break;
            case R.id.layout_4:
                break;
        }
    }
}
