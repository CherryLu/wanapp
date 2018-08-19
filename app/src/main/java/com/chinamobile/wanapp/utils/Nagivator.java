package com.chinamobile.wanapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.ui.activity.GuideActivity;
import com.chinamobile.wanapp.ui.activity.H5ShareActivity;
import com.chinamobile.wanapp.ui.activity.MainActivity;
import com.chinamobile.wanapp.ui.activity.PersonSortActivity;
import com.chinamobile.wanapp.ui.activity.RewardActivity;
import com.chinamobile.wanapp.ui.activity.SignActivity;
import com.chinamobile.wanapp.ui.activity.TaskDetailsActivity;
import com.chinamobile.wanapp.ui.activity.TaskDetailsShareActivity;
import com.chinamobile.wanapp.ui.activity.UploadActivity;
import com.chinamobile.wanapp.ui.activity.WeeksPlanActivity;
import com.chinamobile.wanapp.ui.view.ShareDialog;

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
    public static void startTaskDetailActivity(Context context,TaskData taskData){
        Intent intent = new Intent(context, TaskDetailsActivity.class);
        intent.putExtra("TASK",taskData);
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

    /**
     * 每日任务
     * @param context
     */
    public static void startRewardActivity(Context context){
        Intent intent = new Intent(context, RewardActivity.class);
        context.startActivity(intent);
    }

    /**
     * 每日任务
     * @param context
     */
    public static void startEveryRewardActivity(Context context){
        Intent intent = new Intent(context, RewardActivity.class);
        intent.putExtra("TYPE",1);
        context.startActivity(intent);
    }


    /**
     * 收益排行
     * @param context
     */
    public static void startRewardZhoujihua(Context context){
        Intent intent = new Intent(context, PersonSortActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转下载任务详情
     * @param context
     */
    public static void startTaskDownloadActivity(Context context){
        Intent intent = new Intent(context, TaskDetailsActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转分享任务详情
     * @param context
     */
    public static void startH5TaskShareActivity(Context context){
        Intent intent = new Intent(context, H5ShareActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转分享任务详情
     * @param context
     */
    public static void startTaskShareActivity(Context context){
        Intent intent = new Intent(context, H5ShareActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转签到页面
     * @param context
     */
    public static void startSignActivity(Context context){
        Intent intent = new Intent(context, SignActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转周计划
     * @param context
     */
    public static void startWeeksPlan(Context context){
        Intent intent = new Intent(context, WeeksPlanActivity.class);
        context.startActivity(intent);

    }

    /**
     * 跳转上传页面
     * @param activity
     * @param taskData
     */
    public static void startUploadActivity(Activity activity,TaskData taskData){
        Intent intent = new Intent(activity, UploadActivity.class);
        intent.putExtra("FT",taskData);
        activity.startActivityForResult(intent,100);
    }


    /**
     * 任务点击类型
     * @param context
     * @param taskData
     */
    public static void startTaskOClick(Context context, TaskData taskData){
        AlertHelper helper = new AlertHelper(context);
        if (context==null||taskData==null){
            helper.showError("数据异常，无法解析");
            return;
        }
      String[] strs =  taskData.getParam().split("=");
        if (strs.length>1){
            String page = strs[1];
            switch (page){
                case "101"://分享
                    ShareDialog dialog = new ShareDialog(context);
                    dialog.show();
                    break;
                case "102":
                    Nagivator.startTaskDetailShareActivity(context);
                    break;
                case "103":
                    Nagivator.startTaskDetailActivity(context,taskData);
                    break;
                case "104":
                    break;
                default:
                    helper.showError("数据异常，无法解析");
                    break;
            }
        }else {
            helper.showError("数据异常，无法解析");

        }



    }




}
