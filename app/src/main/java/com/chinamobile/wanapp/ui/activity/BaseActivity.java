package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.ui.view.WaitDialog;
import com.chinamobile.wanapp.utils.AlertHelper;

/**
 * Created by Administrator on 2018/7/26.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if (alertHelper!=null){
                        alertHelper.showLoading();
                    }

                    break;
                case 1:
                    if (alertHelper!=null){
                        alertHelper.dissmiss();
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    };
    public AlertHelper alertHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertHelper = new AlertHelper(this);

    }

    protected void setTitleBar(String title){
        TextView main_title = (TextView) findViewById(R.id.main_title);
        main_title.setText(title);
        ImageView back_image = (ImageView) findViewById(R.id.back_image);
        back_image.setOnClickListener(this);
    }

    protected void setRightTxt(String rightTxt){
        TextView right_txt = (TextView) findViewById(R.id.right_txt);
        ImageView right_image = (ImageView) findViewById(R.id.right_image);
        right_txt.setVisibility(View.VISIBLE);
        right_image.setVisibility(View.GONE);
        right_txt.setText(rightTxt);
    }

    protected  void setRightImage(int image){
        TextView right_txt = (TextView) findViewById(R.id.right_txt);
        ImageView right_image = (ImageView) findViewById(R.id.right_image);
        right_txt.setVisibility(View.GONE);
        right_image.setVisibility(View.VISIBLE);
        right_image.setImageResource(image);
        right_image.setOnClickListener(this);
    }

    protected void backImageOnClick(){

    }

    protected void rightImageOnClick(){

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_image:
                backImageOnClick();
                break;
            case R.id.right_image:
                rightImageOnClick();
                break;

        }
    }


    /**
     * 显示等待dialog
     */
    public void showWait(){
        if (handler!=null){
            handler.sendEmptyMessage(0);
        }
    }

    /**
     * 隐藏等待dialog
     */
    public void hideWaite(){
        if (handler!=null){
            handler.sendEmptyMessage(1);
        }
    }
}
