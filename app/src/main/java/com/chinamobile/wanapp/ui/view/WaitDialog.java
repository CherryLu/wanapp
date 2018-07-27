package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;


/**
 * Created by 95470 on 2018/7/24.
 */

public class WaitDialog extends Dialog {
    private Context mContext;
    private ImageView icon;
    private String contentString = "loading...";
    private TextView content;

    private Animation animation = null;

    public WaitDialog(Context context) {
        super(context, R.style.normal_dialog);
        this.mContext = context;
    }

    private void initWidget() {
        this.icon = ((ImageView) findViewById(R.id.dialog_wait_icon));
        this.content = ((TextView) findViewById(R.id.dialog_wait_content));
    }

    private void init() {
        animation = AnimationUtils.loadAnimation(mContext,
                R.anim.loading_animation);
    }

    private void setAnimation() {
        this.icon.startAnimation(this.animation);
    }

    public void setContent(String paramString) {
        this.contentString = paramString;
        if (this.content != null)
            this.content.setText(this.contentString);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_wait);
        initWidget();
        init();
    }

    public void show() {
        super.show();
        setAnimation();
        this.content.setText(this.contentString);
    }
}
