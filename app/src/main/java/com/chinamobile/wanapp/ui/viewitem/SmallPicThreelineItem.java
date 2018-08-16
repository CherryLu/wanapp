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
 * Created by Administrator on 2018/7/25.
 */

public class SmallPicThreelineItem implements ItemViewDelegate<TaskData> {


    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_small_pic_three;
    }

    @Override
    public boolean isForViewType(TaskData item, int position) {
        if (item.getType()==BaseItem.ITEM_SMALL_PIC_THREE){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, TaskData taskData, int position) {
        holder.setText(R.id.maintitle,taskData.getTitle());
        holder.setText(R.id.lable1,taskData.getJobTags().split(";")[0]);
        holder.setText(R.id.lable2,taskData.getJobTags().split(";")[1]);
        holder.setText(R.id.fanxian,taskData.getJzGain()+"%");
        ImageView item_pic = holder.getConvertView().findViewById(R.id.item_pic);
        GlideUtil.loadImageView(APP.getContext(),taskData.getIconUrl(),item_pic);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Nagivator.startH5TaskShareActivity(v.getContext());

            }
        });
    }




}
