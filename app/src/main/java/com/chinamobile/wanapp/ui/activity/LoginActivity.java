package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/30.
 */

public class LoginActivity extends BaseActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.user_name_input)
    EditText userNameInput;
    @Bind(R.id.user_psw_input)
    EditText userPswInput;
    @Bind(R.id.go_register)
    LinearLayout goRegister;
    @Bind(R.id.forget_psw)
    LinearLayout forgetPsw;
    @Bind(R.id.logion)
    Button logion;
    @Bind(R.id.third_one)
    ImageView thirdOne;
    @Bind(R.id.third_two)
    ImageView thirdTwo;
    @Bind(R.id.third_three)
    ImageView thirdThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitleBar("登录");
    }



    @OnClick({R.id.go_register, R.id.forget_psw, R.id.logion, R.id.third_one, R.id.third_two, R.id.third_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_register:
                break;
            case R.id.forget_psw:
                break;
            case R.id.logion:
                break;
            case R.id.third_one:
                break;
            case R.id.third_two:
                break;
            case R.id.third_three:
                break;
        }
    }
}
