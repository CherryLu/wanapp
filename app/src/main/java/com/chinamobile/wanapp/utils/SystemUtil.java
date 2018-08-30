package com.chinamobile.wanapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
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


    /**
     * 打开第三方APP
     * @param context
     * @param packetName
     */
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


    /**
     *
     */
    public static boolean hasInstanceApp(Context context,String packName){
        if (TextUtils.isEmpty(packName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
