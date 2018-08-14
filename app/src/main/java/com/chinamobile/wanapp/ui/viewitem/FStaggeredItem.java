package com.chinamobile.wanapp.ui.viewitem;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 一页两个 可滑动
 * Created by Administrator on 2018/7/30.
 */

public class FStaggeredItem implements ItemViewDelegate<BaseItem> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_list;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_CARD_BANNER_FIVE){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {
        RecyclerView recyclerView = holder.getConvertView().findViewById(R.id.recyclerview);
        getData();
        setList(recyclerView,holder.getConvertView().getContext());
    }


    private MultiItemTypeAdapter adapter;
    private List<BaseItem> mDatas;

    private void getData(){
        mDatas = new ArrayList<>();
        for (int i =0;i<20;i++){
            BaseItem baseItem = new BaseItem();
            baseItem.setType(BaseItem.ITEM_CARD_FIVE);
            mDatas.add(baseItem);
        }
    }

    private void setList(RecyclerView recyclerView, Context context){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        //StaggeredGridLayoutManager  manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter = new MultiItemTypeAdapter(context,mDatas);
        adapter.addItemViewDelegate(new SmallChildSlideItem());
        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(wrapper);
    }
}
