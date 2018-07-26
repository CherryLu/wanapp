package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.utils.Navigator;

/**
 * Created by Administrator on 2018/7/26.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}
