package com.chinamobile.wanapp.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

public class SystemUtil {

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;

    }



    public static void openOtherAPP(Context context,String packetName){
        if (TextUtils.isEmpty(packetName)){
            Toast.makeText(context,"APP不存在",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packetName);
            if (intent!=null){
                context.startActivity(intent);
            }else {
                Toast.makeText(context,"APP未安装",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
