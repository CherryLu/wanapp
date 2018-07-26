package com.chinamobile.wanapp.utils;

import android.app.Activity;

import com.chinamobile.wanapp.R;

/**
 * Created by Administrator on 2018/7/26.
 */

public class Navigator {

    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(R.anim.no_anim, R.anim.push_right_out);
        }
    }

}
