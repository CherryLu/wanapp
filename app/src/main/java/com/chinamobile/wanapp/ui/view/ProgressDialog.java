package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.ui.callback.UpLoadCallBack;
import com.chinamobile.wanapp.utils.AlertHelper;
import com.chinamobile.wanapp.utils.LogUtils;
import com.chinamobile.wanapp.utils.OssUtils;
import com.chinamobile.wanapp.utils.UserManager;

import java.util.List;

/**
 * Created by Administrator on 2018/8/26.
 */

public class ProgressDialog extends Dialog implements OssUtils.ProgressCallback {

    private TextView title,progress_txt;
    private ProgressBar progressBar;
    private List<String> files;
    private String id;
    private UpLoadCallBack upLoadCallBack;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    double progress = (double) msg.obj;
                    if (progressBar!=null){
                        LogUtils.e("ZX",progress+"");
                        progressBar.setProgress((int) progress);
                    }

                    if (progress_txt!=null){
                        String pr = (int)progress+"";
                        LogUtils.e("ZX",pr+"");
                        progress_txt.setText(pr);
                    }

                    break;
                case 201:
                    currentPosition = currentPosition+1;
                    LogUtils.e("ZX",currentPosition+"");
                    if (currentPosition<files.size()){//继续上传
                        title.setText("第"+(currentPosition+1)+"照片上传中...");
                        ossUtils.beginupload(getContext(), UserManager.getInstance().getId()+"="+id+"="+currentPosition,files.get(currentPosition));
                    }else {//上传结束 上报服务器
                        LogUtils.e("ZX","结束");
                        if (upLoadCallBack!=null){
                            upLoadCallBack.onSuccessed("");
                        }
                        dismiss();
                    }
                    break;

                case 202:
                    if (title!=null){
                        title.setText("上传失败");
                    }

                    if (upLoadCallBack!=null){
                        upLoadCallBack.onFail();
                    }

                    dismiss();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void setUpLoadCallBack(UpLoadCallBack upLoadCallBack) {
        this.upLoadCallBack = upLoadCallBack;
    }

    public ProgressDialog(Context context, List<String> pics,String id) {
        super(context, R.style.normal_dialog);
        files = pics;
        this.id = id;
    }
    private OssUtils ossUtils;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        title = findViewById(R.id.title);
        progress_txt = findViewById(R.id.progress_txt);
        progressBar = findViewById(R.id.progress);

        setCancelable(false);
        setCanceledOnTouchOutside(false);
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        ossUtils = new OssUtils(getContext());
        ossUtils.setProgressCallback(this);
        currentPosition = 0;
        ossUtils.beginupload(getContext(), UserManager.getInstance().getId()+"="+id+"="+currentPosition,files.get(currentPosition));

    }

    @Override
    public void onProgressCallback(double progress) {

        if (handler!=null){
            Message message = Message.obtain();
            message.what = 200;
            message.obj = progress;
            handler.sendMessage(message);
        }

    }

    @Override
    public void onSuccess(String name) {

        if (handler!=null){
            handler.sendEmptyMessage(201);
        }


    }

    @Override
    public void onFails() {

        if (handler!=null){
            handler.sendEmptyMessage(202);
        }


    }
}
