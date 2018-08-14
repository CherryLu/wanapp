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

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.utils.GlideUtil;

/**
 * Created by Administrator on 2018/8/14.
 */

public class MainDialog extends Dialog {

    private TaskData taskData;

    public MainDialog(Context context,TaskData taskData) {
        super(context, R.style.normal_dialog);
        this.taskData = taskData;
    }

    private ImageView imageView;
    private ImageView  close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main);
        imageView = findViewById(R.id.imgrview);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        close = findViewById(R.id.close_pic);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setTaskData(taskData);

    }

    /**
     *
     * @param taskData
     */
    public void setTaskData(TaskData taskData) {
        if (taskData==null){
            return;
        }
        if (imageView!=null){
            GlideUtil.loadImageView(getContext(),taskData.getImgUrl(),imageView);
        }
    }
}
