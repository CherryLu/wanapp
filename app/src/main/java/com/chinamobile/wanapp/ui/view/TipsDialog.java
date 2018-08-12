package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;

public class TipsDialog extends Dialog implements View.OnClickListener {


    @Bind(R.id.close_pic)
    ImageView closePic;
    @Bind(R.id.one_step_pic)
    ImageView oneStepPic;
    @Bind(R.id.one_step_txt)
    TextView oneStepTxt;
    @Bind(R.id.two_step_pic)
    ImageView twoStepPic;
    @Bind(R.id.two_step_txt)
    TextView twoStepTxt;
    @Bind(R.id.three_step_pic)
    ImageView threeStepPic;
    @Bind(R.id.three_step_txt)
    TextView threeStepTxt;
    @Bind(R.id.four_step_pic)
    ImageView fourStepPic;
    @Bind(R.id.four_step_txt)
    TextView fourStepTxt;

    public TipsDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    public TipsDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_tips);
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        closePic = findViewById(R.id.close_pic);
        closePic.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_pic:
                dismiss();
                break;
        }
    }
}

