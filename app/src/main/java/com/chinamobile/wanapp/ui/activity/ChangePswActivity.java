package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 95470 on 2018/7/31.
 */

public class ChangePswActivity extends BaseActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.pwd_lock)
    ImageView pwdLock;
    @Bind(R.id.iv_eyes)
    ImageView ivEyes;
    @Bind(R.id.et_new_pwd)
    EditText etNewPwd;
    @Bind(R.id.layout_mode_1)
    RelativeLayout layoutMode1;
    @Bind(R.id.et_old_pwd)
    EditText etOldPwd;
    @Bind(R.id.et_new_pwd1)
    EditText etNewPwd1;
    @Bind(R.id.et_new_pwd2)
    EditText etNewPwd2;
    @Bind(R.id.layout_mode_2)
    LinearLayout layoutMode2;
    @Bind(R.id.btn_done)
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_image, R.id.iv_eyes, R.id.btn_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                break;
            case R.id.iv_eyes:
                break;
            case R.id.btn_done:
                break;
        }
    }
}
