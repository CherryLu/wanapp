package com.chinamobile.wanapp.ui.viewitem;

import android.support.v7.widget.CardView;
import android.view.View;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/8/12.
 */

public class TwoCardItem implements ItemViewDelegate<BaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_two_card;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_TWO_CARD){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {

        CardView cv_content = holder.getConvertView().findViewById(R.id.cv_content);


        cv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nagivator.startRewardActivity(view.getContext());
            }
        });

        CardView cv_content1 = holder.getConvertView().findViewById(R.id.cv_content1);


        cv_content1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nagivator.startTaskShareActivity(view.getContext());
            }
        });
    }
}
