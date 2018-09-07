package com.chinamobile.wanapp.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Administrator on 2018/7/26.
 */

public class BaseFragment extends Fragment {

    View mRootView;

    public static final int WAITE_TIME = 500;
    public static final int REFRESH_SUCCESS = 200;
    public static final int REFRESH_ERROR = 201;
    public static final int LOAD_MORE_SUCCESS = 202;
    public static final int LOAD_MORE_ERROR = 203;

}
