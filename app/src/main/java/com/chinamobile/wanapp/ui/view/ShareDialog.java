package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chinamobile.wanapp.R;

/**
 * Created by Administrator on 2018/8/16.
 */

public class ShareDialog extends Dialog implements View.OnClickListener {

    public ShareDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    private LinearLayout qq_share,qqzone_share,weixin_share,weixinsq_share,sina_share;
    private Button cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_dialog);
        qq_share = findViewById(R.id.qq_share);
        qq_share.setOnClickListener(this);
        qqzone_share = findViewById(R.id.qqzone_share);
        qqzone_share.setOnClickListener(this);
        weixin_share = findViewById(R.id.weixin_share);
        weixin_share.setOnClickListener(this);
        weixinsq_share = findViewById(R.id.weixinsq_share);
        weixinsq_share.setOnClickListener(this);
        sina_share = findViewById(R.id.sina_share);
        sina_share.setOnClickListener(this);
        cancle = findViewById(R.id.cancle);
        cancle.setOnClickListener(this);

        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qq_share:

                break;
            case R.id.qqzone_share:

                break;
            case R.id.weixin_share:

                break;
            case R.id.weixinsq_share:

                break;
            case R.id.sina_share:

                break;
            case R.id.cancle:
                dismiss();
                break;
        }
    }
}
