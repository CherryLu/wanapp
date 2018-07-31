package com.chinamobile.wanapp.ui.viewitem;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by 95470 on 2018/7/31.
 */

public class TitleItem implements ItemViewDelegate<BaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_title;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_TITLE){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {

    }
}
