package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.APP;
import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TitleMessage;
import com.chinamobile.wanapp.ui.fragment.SortFragment;
import com.chinamobile.wanapp.ui.view.PagerSlidingTabStrip;
import com.chinamobile.wanapp.utils.LogUtils;
import com.chinamobile.wanapp.utils.Nagivator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/10.
 */

public class SortActivity extends BaseActivity {


    @Bind(R.id.layout_title)
    LinearLayout layoutTitle;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.pagerSlidingTabStrip)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;

    private List<TitleMessage> messages;

    private List<SortFragment> sortFragments;

    private FragmentAdapter adapter;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
        setTitleBar("分类任务");
        sortFragments = new ArrayList<>();
        messages = APP.titleMessages;
        position = getIntent().getIntExtra("POS",0);
        initTitle();

    }


    private void initTitle() {
        if (messages == null || messages.size() <= 0) {
            return;
        }
        layoutTitle.removeAllViews();
        for (int i = 0; i < messages.size(); i++) {
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.tv_main_tab, layoutTitle, false);
            textView.setText(messages.get(i).getTitle());
            LogUtils.e("ZXZXZX", messages.get(i).getTitle());
            layoutTitle.addView(textView);
            SortFragment sortFragment = new SortFragment();
            Bundle bundle = new Bundle();
            bundle.putString("MID", messages.get(i).getMid());
            sortFragment.setArguments(bundle);
            sortFragments.add(sortFragment);

        }

        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(1);
        pagerSlidingTabStrip.setViewPager(viewpager);

        LogUtils.e("POS",position+"");

        viewpager.setCurrentItem(position);
    }

    @OnClick(R.id.back_image)
    public void onClick() {
        Nagivator.finishActivity(this);
    }


    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return sortFragments.get(position);
        }

        @Override
        public int getCount() {
            return sortFragments.size();
        }
    }
}
