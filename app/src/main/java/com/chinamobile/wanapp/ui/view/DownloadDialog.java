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

    public DownloadDialog(@NonNull Context context) {
        super(context, R.style.normal_dialog);
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
        ApiServiceManager.fileDownLoad("http://imtt.dd.qq.com/16891/010A36E0CE3DCDB3FB4811F606CDFE18.apk?fsname=com.tencent.tmgp.dkmhjqy_2.1.22_72.apk&csr=1bbd","downloaddemo.apk",handler);
    }
}
