package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/10.
 */

public class SortActivity extends BaseActivity {


    @Bind(R.id.layout_title)
    LinearLayout layoutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
    }
}
