package com.chinamobile.wanapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.ui.activity.GuideActivity;
import com.chinamobile.wanapp.ui.activity.MainActivity;
import com.chinamobile.wanapp.ui.activity.TaskDetailsActivity;
import com.chinamobile.wanapp.ui.activity.TaskDetailsShareActivity;

public class Nagivator {



    public static void finishActivity(Activity activity){
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(R.anim.no_anim, R.anim.push_right_out);
        }
    }

    /**
     * 跳转任务详情页
     * @param context
     */
    public static void startTaskDetailActivity(Context context){
        Intent intent = new Intent(context, TaskDetailsActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转分享任务详情页
     * @param context
     */
    public static void startTaskDetailShareActivity(Context context){
        Intent intent = new Intent(context, TaskDetailsShareActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转主页
     * @param context
     */
    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转引导页
     * @param context
     */
    public static void startGuideActivity(Context context){
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }




}
