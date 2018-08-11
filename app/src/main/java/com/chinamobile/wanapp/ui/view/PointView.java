package com.chinamobile.wanapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.utils.ScreenUtil;

/**
 * Created by July on 2017/8/1.
 * 引导图或幻灯上面的点点
 */

public class PointView extends LinearLayout {
    public PointView(Context context) {
        this(context, null);
    }

    public PointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    /**
     * 设置当前选中的点点位置
     * @param position
     */
    public void setSelectedPosition(int position)
    {
        int count = getChildCount();
        for (int i = 0; i < count; i++)
        {
            getChildAt(i).setEnabled(i == position);
        }
    }

    /**
     * 添加点点（外部调用接口）
     * @param size
     */
    public void addPoints(int size)
    {
        addPointBtn(size, R.drawable.point_btn_bg, 8, 8, 16);
    }

    /**
     * 添加点点
     * @param size 点点个数
     * @param imageId
     * @param width 单位dp
     * @param height 单位dp
     * @param margin 单位dp
     */
    private void addPointBtn(int size, int imageId, int width, int height, int margin)
    {
        removeAllViews();
        if (size <= 0)
        {
            return;
        }
        ImageView imageView;
        for (int i = 0; i < size; i++)
        {
            imageView = new ImageView(getContext());

            imageView.setBackgroundResource(imageId);
            imageView.setEnabled(false);
            addView(imageView, ScreenUtil.dip2px(getContext(), width), ScreenUtil.dip2px(getContext(), height));

            LayoutParams params = (LayoutParams) imageView.getLayoutParams();
            if(i == size - 1)
            {
                params.setMargins(0, 0, 0, 0);
            }
            else
            {
                params.setMargins(0, 0, ScreenUtil.dip2px(getContext(), margin), 0);
            }
        }
    }
}
