package com.chinamobile.wanapp.ui.viewitem;

import android.util.Log;
import android.view.View;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/7/25.
 */

public class SmallPicThreelineItem implements ItemViewDelegate<BaseItem> {


    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_small_pic_three;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        Log.e("ZX","position : "+position+"    getType"+item.getType());
        if (item.getType()==BaseItem.ITEM_SMALL_PIC_THREE){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nagivator.startH5TaskShareActivity(v.getContext());
            }
        });
    }
}
