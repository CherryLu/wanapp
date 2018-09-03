package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TaskData;

/**
 * Created by Administrator on 2018/8/16.
 */

public class ShareDialog extends Dialog implements View.OnClickListener {


    private String page;

    private TaskData taskData;

    public void setTaskData(TaskData taskData) {
        this.taskData = taskData;
    }

    public ShareDialog(Context context) {
        super(context, R.style.MyDialog);
    }


    public ShareDialog(Context context,String page) {
        super(context, R.style.MyDialog);
        this.page = page;
    }

    private LinearLayout qq_share,qqzone_share,weixin_share,weixinsq_share,sina_share;
    private Button cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_dialog);

        qq_share = findViewById(R.id.qq_share);
        qq_share.setOnClickListener(this);
        qq_share.setVisibility(View.GONE);

        qqzone_share = findViewById(R.id.qqzone_share);
        qqzone_share.setOnClickListener(this);
        qqzone_share.setVisibility(View.GONE);

        weixin_share = findViewById(R.id.weixin_share);
        weixin_share.setOnClickListener(this);
        weixin_share.setVisibility(View.GONE);

        weixinsq_share = findViewById(R.id.weixinsq_share);
        weixinsq_share.setOnClickListener(this);
        weixinsq_share.setVisibility(View.GONE);

        sina_share = findViewById(R.id.sina_share);
        sina_share.setOnClickListener(this);
        sina_share.setVisibility(View.GONE);

        cancle = findViewById(R.id.cancle);
        cancle.setOnClickListener(this);

        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        setShowIt();




    }


    private void setShowIt(){

        if (TextUtils.isEmpty(page)){
            return;
        }

        String endPage = page.substring(3,page.length());
        if (TextUtils.isEmpty(endPage)){
            qq_share.setVisibility(View.VISIBLE);
            qqzone_share.setVisibility(View.VISIBLE);
            weixin_share.setVisibility(View.VISIBLE);
            weixinsq_share.setVisibility(View.VISIBLE);
            sina_share.setVisibility(View.VISIBLE);

        }

        if (endPage.contains("1")){
            qq_share.setVisibility(View.VISIBLE);
        }

        if (endPage.contains("2")){
            qqzone_share.setVisibility(View.VISIBLE);
        }

        if (endPage.contains("3")){
            weixin_share.setVisibility(View.VISIBLE);
        }

        if (endPage.contains("4")){
            weixinsq_share.setVisibility(View.VISIBLE);
        }

        if (endPage.contains("5")){
            weixinsq_share.setVisibility(View.VISIBLE);
        }
    }


    private void share(Context context){
        if (taskData==null||taskData.getJobStr()==null){
            Toast.makeText(context,"数据为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(context,"url : "+taskData.getJobStr().getShareData().get(0).getShareUrl()+"     image : "+taskData.getJobStr().getShareData().get(0).getShareImage()+"      txt :"+taskData.getJobStr().getShareData().get(0).getShareTxt(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qq_share:
                share(view.getContext());
                break;
            case R.id.qqzone_share:
                share(view.getContext());
                break;
            case R.id.weixin_share:
                share(view.getContext());
                break;
            case R.id.weixinsq_share:
                share(view.getContext());
                break;
            case R.id.sina_share:
                share(view.getContext());
                break;
            case R.id.cancle:
                dismiss();
                break;
        }
    }
}
