package com.chinamobile.wanapp.utils;

import android.content.Context;

public class ScreenUtil {


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
