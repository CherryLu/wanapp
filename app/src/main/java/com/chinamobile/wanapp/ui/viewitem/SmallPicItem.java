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
    public void convert(ViewHolder holder, final TaskData taskData, int position) {
        if (taskData==null){
            return;
        }
        holder.setText(R.id.maintitle,taskData.getTitle());
        holder.setText(R.id.subtitle,taskData.getSubtitle());
        holder.setText(R.id.last_order,(taskData.getLimiteCount()-taskData.getDoneCount())+"");
        holder.setText(R.id.shouyi,(taskData.getJzGain()/100d)+"元");
        ImageView pic = holder.getView(R.id.item_pic);
        GlideUtil.loadImageView(APP.getContext(),taskData.getIconUrl(),pic);

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nagivator.startTaskOClick(v.getContext(),taskData);

            }
        });
    }

}
