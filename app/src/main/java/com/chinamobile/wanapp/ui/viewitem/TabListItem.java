package com.chinamobile.wanapp.ui.viewitem;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.TitleMessage;
import com.chinamobile.wanapp.ui.activity.BaseActivity;
import com.chinamobile.wanapp.ui.view.TitleList;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95470 on 2018/7/30.
 */

public class TabListItem implements ItemViewDelegate<BaseItem> {



    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_tab_list;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_TAB_LIST){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {
        TitleList titleList = holder.getConvertView().findViewById(R.id.titleList);

        List<TitleMessage> messages = new ArrayList<>();

        for (int i=0;i<baseItem.getDataList().size();i++){
            if ("1".equals(baseItem.getDataList().get(i).getPid())){

                TitleMessage message = new TitleMessage();
                message.setMid(baseItem.getDataList().get(i).getId());
                message.setTitle(baseItem.getDataList().get(i).getMname());

                messages.add(message);

            }
        }
        titleList.setMessages(messages);
        titleList.setDefaultData(baseItem.getFirstData());

    }
}
