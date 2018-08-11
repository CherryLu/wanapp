package com.chinamobile.wanapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.ui.view.PointView;
import com.chinamobile.wanapp.utils.Constant;
import com.chinamobile.wanapp.utils.Nagivator;
import com.chinamobile.wanapp.utils.SharedPreferencesUtils;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {
    private static final String TAG = "WelcomeGuideActivity";
    /**
     * 引导图个数
     */
    private static final int COUNTS = 2;

    private static final int MAX_CACHE_COUNT = 2;

    private ViewPager viewPager;



    /**
     * View缓存,考虑view的复用，只需要三个view就够了
     */
    private ArrayList<View> viewList = new ArrayList<View>(MAX_CACHE_COUNT);

    private GuideAdapter adapter;

    /**
     * 当前在第几个图片
     */
    private int currentPosition;

    /**
     * 引导图下方的点点，会突出显示当前滑动到第几个
     */
    private PointView pointView;

    // 本地图片id
    private int[] resIds = {R.mipmap.accelerate_guide1, R.mipmap.accelerate_guide2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_welcome_guide);
        initViews();

    }

    private void initViews() {
        viewList.clear();
        for (int i = 0; i < MAX_CACHE_COUNT; i++)
        {
            View pageView = View.inflate(this, R.layout.welcome_guide_view, null);
            ViewHolder holder = new ViewHolder();
            holder.image = (ImageView) pageView.findViewById(R.id.guide_image);
            holder.skip = (TextView) pageView.findViewById(R.id.skip);
            holder.entry = (ImageView) pageView.findViewById(R.id.use_at_once);
            pageView.setTag(holder);
            viewList.add(pageView);
        }
        viewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        adapter = new GuideAdapter();
        viewPager.setAdapter(adapter);
        // 为 1 的时候可以不用手动设置了，默认就是 1
        // viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                currentPosition = position;
                pointView.setSelectedPosition(position);
                Log.d(TAG, " onPageSelected position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            float startX, endX;

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        try
                        {
                            endX = event.getX();

                            // 首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否大于某段距离，这里的判断距离是屏幕宽度的四分之一（可以适当控制）
                            if (currentPosition == (COUNTS - 1)
                                    && (startX - endX) >= (screenWidthPx(GuideActivity.this) / 4))
                            {
                                enterMainActivity();
                            }
                        }
                        catch (Exception e)
                        {
                            Log.e("Exception", e + "");
                        }
                        break;
                }
                return false;
            }
        });
        // 添加点点
        pointView = (PointView) findViewById(R.id.point_view);
        pointView.addPoints(COUNTS);
        pointView.setSelectedPosition(0);
    }

    class GuideAdapter extends PagerAdapter
    {
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            View view = createItemView(position);
            container.removeView(view);
            container.addView(view);
            Log.d(TAG, " instantiateItem position = " + position + ",view pos = " + position % MAX_CACHE_COUNT + ",container size = " + container.getChildCount());
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            // 不在此处删除（在此处删除，显示可能会有问题），在instantiateItem里addView前删除
            // container.removeView(viewList.get(position % MAX_CACHE_COUNT));
            Log.d(TAG, " destroyItem position = " + position);
        }

        @Override
        public int getCount()
        {
            return COUNTS;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }
    }

    /**
     * ViewPager 每一页View
     *
     * @param position
     * @return
     */
    private View createItemView(int position)
    {
        if (position >= COUNTS || position < 0)
        {
            return null;
        }
        //  注意这里要取缓存列表里的View，所以position范围只能是0,1,2,取模即可
        int pos = position % MAX_CACHE_COUNT;
        View view = viewList.get(pos);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.image.setImageResource(resIds[position]);
        View useAtOnce = holder.entry;
        View skip = holder.skip;
        skip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                enterMainActivity();
            }
        });
        if (position < COUNTS - 1)
        {
            // 只显示右上角"跳过"
            useAtOnce.setVisibility(View.GONE);
//            skip.setVisibility(View.GONE);
        }
        else if (position == COUNTS - 1)
        {
            // 最后一页
            useAtOnce.setVisibility(View.VISIBLE);
//            skip.setVisibility(View.GONE);
        }

        useAtOnce.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                enterMainActivity();
            }
        });
        return view;
    }

    /**
     * 关闭引导界面，进入加速页
     */
    private void enterMainActivity() {

        SharedPreferencesUtils.setParam(this, Constant.TAG_GUIDE,true);
        Nagivator.startMainActivity(this);
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);


    }

    /**
     * 小的为屏幕宽度
     *
     * @param context
     * @return
     */
    public static int screenWidthPx(Context context)
    {
        int widthPx = context.getResources().getDisplayMetrics().widthPixels;
        int heightPx = context.getResources().getDisplayMetrics().heightPixels;
        return widthPx > heightPx ? heightPx : widthPx;
    }

    private static class ViewHolder
    {
        /**
         * 引导图
         */
        public ImageView image;

        /**
         * 跳过
         */
        public TextView skip;

        /**
         * 立即使用按钮
         */
        public ImageView entry;
    }
}
