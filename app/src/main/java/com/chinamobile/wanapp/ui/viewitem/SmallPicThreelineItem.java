package com.chinamobile.wanapp.ui.viewitem;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/7/25.
 */

public class SmallPicThreelineItem implements ItemViewDelegate<BaseItem> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_small_pic;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_SMALL_PIC){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {

    }
}
