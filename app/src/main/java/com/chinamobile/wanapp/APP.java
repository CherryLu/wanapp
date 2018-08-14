package com.chinamobile.wanapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/8/10.
 */

public class APP extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


    public static Context getContext() {
        return context;
    }
}
