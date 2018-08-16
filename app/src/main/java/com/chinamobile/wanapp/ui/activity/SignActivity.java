package com.chinamobile.wanapp.ui.activity;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.utils.AlertHelper;
import com.chinamobile.wanapp.utils.Nagivator;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/8/15.
 */

public class SignActivity extends BaseActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.sign_btn)
    ImageView signBtn;
    @Bind(R.id.layout1_image)
    ImageView layout1Image;
    @Bind(R.id.layout1)
    LinearLayout layout1;
    @Bind(R.id.layout2_image)
    ImageView layout2Image;
    @Bind(R.id.layout2)
    LinearLayout layout2;
    @Bind(R.id.layout3_image)
    ImageView layout3Image;
    @Bind(R.id.layout3)
    LinearLayout layout3;
    @Bind(R.id.layout4_image)
    ImageView layout4Image;
    @Bind(R.id.layout4)
    LinearLayout layout4;
    @Bind(R.id.layout5_image)
    ImageView layout5Image;
    @Bind(R.id.layout5)
    LinearLayout layout5;
    @Bind(R.id.layout6_image)
    ImageView layout6Image;
    @Bind(R.id.layout6)
    LinearLayout layout6;
    @Bind(R.id.layout7_image)
    ImageView layout7Image;
    @Bind(R.id.layout7)
    LinearLayout layout7;

    Animation mAnimation;
    int weekday;
    ImageView[] imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiandao);
        ButterKnife.bind(this);
        getData();
        imageViews = new ImageView[]{layout1Image,layout2Image,layout3Image,layout4Image,layout5Image,layout6Image,layout7Image};
        mAnimation = AnimationUtils.loadAnimation(this,R.anim. balloonscale);
        signBtn.setAnimation(mAnimation );
        mAnimation.start();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
         weekday=c.get(Calendar.DAY_OF_WEEK);
        if (weekday==1){
            weekday = 7;
        }else {
            weekday = weekday-1;
        }

    }


    private void getData(){
        ApiServiceManager.getSignData(new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @OnClick({R.id.back_image, R.id.sign_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.sign_btn:

                AlertHelper helper = new AlertHelper(view.getContext());
                helper.showSuccess();

                imageViews[weekday-1].setImageResource(R.mipmap.qiandao_packet_open);

                break;
        }
    }
}
