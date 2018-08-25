package com.chinamobile.wanapp.ui.viewitem;

import android.view.View;
import android.widget.RelativeLayout;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by 95470 on 2018/7/28.
 */

public class Icon4Item implements ItemViewDelegate<BaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_icon;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_ICON4){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {

        RelativeLayout layout1 = holder.getConvertView().findViewById(R.id.layout1);
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nagivator.startEveryRewardActivity(view.getContext());
            }
        });


        RelativeLayout layout2 = holder.getConvertView().findViewById(R.id.layout2);
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nagivator.startSignActivity(view.getContext());
            }
        });

        RelativeLayout layout3= holder.getConvertView().findViewById(R.id.layout3);
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nagivator.startInvitationActivity(view.getContext());
            }
        });

        RelativeLayout layout4 = holder.getConvertView().findViewById(R.id.layout4);
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nagivator.startRewardZhoujihua(view.getContext());


            }
        });

    }
}
