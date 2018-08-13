package com.chinamobile.wanapp.ui.viewitem;

import android.view.View;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/7/25.
 */

public class SmallPicItem implements ItemViewDelegate<TaskData> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_small_pic;
    }

    @Override
    public boolean isForViewType(TaskData item, int position) {
        if (item.getType()==BaseItem.ITEM_SMALL_PIC){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, TaskData taskData, int position) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nagivator.startTaskDownloadActivity(v.getContext());
            }
        });
    }

}
