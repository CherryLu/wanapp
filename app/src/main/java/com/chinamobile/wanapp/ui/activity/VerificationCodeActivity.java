package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 95470 on 2018/7/31.
 */

public class VerificationCodeActivity extends BaseActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.button_get_sms)
    Button buttonGetSms;
    @Bind(R.id.btn_done)
    Button btnDone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        ButterKnife.bind(this);
        setTitleBar("注册");
    }
    boolean isTimerStart;
    CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            buttonGetSms.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            buttonGetSms.setText("获取验证码");
            buttonGetSms.setClickable(true);
            buttonGetSms.setBackgroundResource(R.drawable.bg_login_button);
            isTimerStart = false;
        }
    };


    @OnClick({R.id.back_image, R.id.button_get_sms, R.id.btn_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                break;
            case R.id.button_get_sms:
                break;
            case R.id.btn_done:
                break;
        }
    }
}
