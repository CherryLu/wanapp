package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.http.ApiServiceManager;

public class DownloadDialog extends Dialog {




    public DownloadDialog(@NonNull Context context,TaskData taskData) {
        super(context, R.style.normal_dialog);
        this.taskData = taskData;
    }


    private TextView title,progress_txt;
    private ProgressBar progressBar;

    private TaskData taskData;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                   int progress  = (int) msg.obj;
                   if (progressBar!=null){
                       progressBar.setProgress(progress);
                   }

                   if (progress>=1){//下载完成
                       title.setText("下载完成");//跳转安装页面
                       dismiss();
                   }



                    break;
                case 201:

                        if (title!=null){
                            title.setText("下载错误");
                        }

                    break;

            }
            super.handleMessage(msg);
        }
    };

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

        downloadAPK();

    }



    private void downloadAPK(){
        ApiServiceManager.fileDownLoad("http://183.222.103.13/cache/imtt.dd.qq.com/16891/758F982E62B220A8D1AB507C6877B306.apk?fsname=com.tencent.weishi_4.6.0.588_460.apk&csr=1bbd&ich_args2=317-30101712037112_531cd4b6e26b76a59962aaa0df29fad5_10004302_9c896729d2caf4d5923c518939a83798_29b123687c4ef48198846bedc81d10ba","downloaddemo.apk",handler);
    }
}
