package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.OnClick;

public class BottomDialog extends Dialog {

    @Bind(R.id.header_pic)
    ImageView headerPic;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.label_layout)
    LinearLayout labelLayout;
    @Bind(R.id.subtitle)
    TextView subtitle;
    @Bind(R.id.line)
    VideoView line;
    @Bind(R.id.tips)
    TextView tips;
    @Bind(R.id.shuoming)
    TextView shuoming;
    @Bind(R.id.copy_name)
    TextView copyName;
    @Bind(R.id.copy_layout)
    LinearLayout copyLayout;
    @Bind(R.id.start_btn)
    Button startBtn;

    public BottomDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @OnClick({R.id.copy_name, R.id.start_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.copy_name:
                break;
            case R.id.start_btn:
                break;
        }
    }
}
