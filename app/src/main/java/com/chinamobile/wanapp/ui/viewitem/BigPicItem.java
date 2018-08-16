package com.chinamobile.wanapp.ui.viewitem;

import android.view.View;
import android.widget.ImageView;

import com.chinamobile.wanapp.APP;
import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.utils.GlideUtil;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by 95470 on 2018/7/31.
 */

public class BigPicItem implements ItemViewDelegate<TaskData> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_bigpic;
    }

    @Override
    public boolean isForViewType(TaskData item, int position) {
        if (item.getType()==BaseItem.ITEM_BIG_PIC){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, TaskData taskData, int position) {

        ImageView imageView = holder.getConvertView().findViewById(R.id.item_pic);
        GlideUtil.loadImageView(APP.getContext(),taskData.getIconUrl(),imageView);
        holder.setText(R.id.txt,taskData.getTitle());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nagivator.startTaskDetailShareActivity(v.getContext());
            }
        });
    }



}
