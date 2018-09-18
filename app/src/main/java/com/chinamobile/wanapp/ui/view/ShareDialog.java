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

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

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

    private static final int QQ = 0;
    private static final int QQ_ZONE = 1;
    private static final int WECHAT = 2;
    private static final int WECHAT_MENT = 3;
    private static final int SINA = 4;

    private void share(Context context,int which){
        if (taskData==null||taskData.getJobStr()==null){
            Toast.makeText(context,"数据为空",Toast.LENGTH_SHORT).show();
            return;
        }

        switch (which){
            case QQ: {
                cn.sharesdk.tencent.qq.QQ.ShareParams shareParams = new QQ.ShareParams();
                shareParams.setImageUrl(taskData.getJobStr().getShareData().get(0).getShareImage());
                shareParams.setTitleUrl(taskData.getJobStr().getShareData().get(0).getShareUrl());
                shareParams.setTitle(taskData.getJobStr().getShareData().get(0).getShareTxt());
                shareParams.setText(taskData.getJobStr().getShareData().get(0).getShareTxt());
                Platform platform = ShareSDK.getPlatform(cn.sharesdk.tencent.qq.QQ.NAME);
                platform.share(shareParams);
            }
                break;
            case QQ_ZONE: {
                cn.sharesdk.tencent.qzone.QZone.ShareParams shareParams = new QZone.ShareParams();
                shareParams.setImageUrl(taskData.getJobStr().getShareData().get(0).getShareImage());
                shareParams.setTitleUrl(taskData.getJobStr().getShareData().get(0).getShareUrl());
                shareParams.setTitle(taskData.getJobStr().getShareData().get(0).getShareTxt());
                shareParams.setText(taskData.getJobStr().getShareData().get(0).getShareTxt());
                Platform platform = ShareSDK.getPlatform(QZone.NAME);
                platform.share(shareParams);
            }
                break;
            case WECHAT:{
                Wechat.ShareParams shareParams = new Wechat.ShareParams();
                shareParams.setTitle(taskData.getJobStr().getShareData().get(0).getShareTxt());
                shareParams.setUrl(taskData.getJobStr().getShareData().get(0).getShareUrl());
                shareParams.setImageUrl(taskData.getJobStr().getShareData().get(0).getShareImage());
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                Platform platform = ShareSDK.getPlatform(Wechat.NAME);
                platform.share(shareParams);
            }
                break;
            case WECHAT_MENT: {
                WechatMoments.ShareParams shareParams = new WechatMoments.ShareParams();
                shareParams.setTitle(taskData.getJobStr().getShareData().get(0).getShareTxt());
                shareParams.setUrl(taskData.getJobStr().getShareData().get(0).getShareUrl());
                shareParams.setImageUrl(taskData.getJobStr().getShareData().get(0).getShareUrl());
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
                platform.share(shareParams);
            }
                break;
            case SINA:
                SinaWeibo.ShareParams shareParams = new SinaWeibo.ShareParams();
                shareParams.setTitle(taskData.getJobStr().getShareData().get(0).getShareTxt());
                shareParams.setText(taskData.getJobStr().getShareData().get(0).getShareUrl());
                shareParams.setImageUrl(taskData.getJobStr().getShareData().get(0).getShareUrl());
                Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
                platform.share(shareParams);
                break;
        }

        Toast.makeText(context,"url : "+taskData.getJobStr().getShareData().get(0).getShareUrl()+"     image : "+taskData.getJobStr().getShareData().get(0).getShareImage()+"      txt :"+taskData.getJobStr().getShareData().get(0).getShareTxt(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qq_share:
                share(view.getContext(),QQ);
                break;
            case R.id.qqzone_share:
                share(view.getContext(),QQ_ZONE);
                break;
            case R.id.weixin_share:
                share(view.getContext(),WECHAT);
                break;
            case R.id.weixinsq_share:
                share(view.getContext(),WECHAT_MENT);
                break;
            case R.id.sina_share:
                share(view.getContext(),SINA);
                break;
            case R.id.cancle:
                dismiss();
                break;
        }
    }
}
