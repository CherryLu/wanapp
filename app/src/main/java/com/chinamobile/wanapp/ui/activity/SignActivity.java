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
import com.chinamobile.wanapp.baen.BaseSignBean;
import com.chinamobile.wanapp.baen.SignData;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.utils.AlertHelper;
import com.chinamobile.wanapp.utils.Nagivator;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    List<SignData> signDatas  = new ArrayList<SignData>();
    private void getData(){
        ApiServiceManager.getSignData(new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseSignBean signBean = gson.fromJson(json,BaseSignBean.class);
                    signDatas.addAll(signBean.getSignBean().getSignData());
                    initView();


                } catch (IOException e) {
                    e.printStackTrace();
                    initView();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                initView();
            }
        });
    }


    private void initView(){
        if (signDatas==null){
            return;
        }
        for (int i=0;i<signDatas.size();i++){
            int day = signDatas.get(i).getData();
            if (day>0&&day<7){
                imageViews[weekday-1].setImageResource(R.mipmap.qiandao_packet_open);
            }


        }
    }

    @OnClick({R.id.back_image, R.id.sign_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.sign_btn:
                signTodat();


                break;
        }
    }


    private void signTodat(){
        ApiServiceManager.getSign(new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    JSONObject object = new JSONObject(json);
                    JSONObject object1 = (JSONObject) object.get("userData");
                    String str = object1.getString("flag");
                    if ("签到成功".equals(str)){
                      alertHelper.showSuccess();
                     imageViews[weekday-1].setImageResource(R.mipmap.qiandao_packet_open);
                    }else if ("已经签到".equals(str)){
                        alertHelper.showSTips("今日已经签到，请改日再来");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    alertHelper.showSTips("今日已经签到，请改日再来");
                } catch (JSONException e) {
                    e.printStackTrace();
                    alertHelper.showSTips("今日已经签到，请改日再来");
                }
            }

            @Override
            public void onError(Throwable e) {
                alertHelper.showSTips("今日已经签到，请改日再来");
            }
        });
    }
}
