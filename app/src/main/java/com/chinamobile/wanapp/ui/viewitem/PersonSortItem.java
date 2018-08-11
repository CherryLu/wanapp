package com.chinamobile.wanapp.ui.viewitem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/8/12.
 */

public class PersonSortItem implements ItemViewDelegate<BaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_person;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_PERSON_LIST){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {
        ImageView pos_pic = holder.getConvertView().findViewById(R.id.logo_position);
        TextView pos_txt = holder.getConvertView().findViewById(R.id.position);
        if (position==0){
            pos_pic.setImageResource(R.mipmap.first);
            pos_txt.setVisibility(View.GONE);

        }else if (position==1){
            pos_pic.setImageResource(R.mipmap.second);
            pos_txt.setVisibility(View.GONE);

        }else if (position==2){
            pos_pic.setImageResource(R.mipmap.third);
            pos_txt.setVisibility(View.GONE);

        }else {
            pos_pic.setVisibility(View.GONE);
            pos_txt.setVisibility(View.VISIBLE);
            pos_txt.setText(position+1+"");

        }
    }
}
