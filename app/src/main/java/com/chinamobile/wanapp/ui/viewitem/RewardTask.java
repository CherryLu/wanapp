package com.chinamobile.wanapp.ui.viewitem;

import android.view.View;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.Welfare;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/8/12.
 */

public class RewardTask implements ItemViewDelegate<Welfare> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_paihang_task;
    }

    @Override
    public boolean isForViewType(Welfare item, int position) {
        return true;
    }

    @Override
    public void convert(ViewHolder holder, Welfare welfare, int position) {

        if ((welfare.getJujDc()/welfare.getRequest())>=1){//任务完场

        }else {//任务未完成

        }

        holder.setText(R.id.maintitle,welfare.getRemark());
    }

}
