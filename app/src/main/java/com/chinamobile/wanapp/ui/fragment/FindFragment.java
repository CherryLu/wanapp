package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.ui.viewitem.BigPicItem;
import com.chinamobile.wanapp.ui.viewitem.FStaggeredItem;
import com.chinamobile.wanapp.ui.viewitem.Icon3Item;
import com.chinamobile.wanapp.ui.viewitem.SmallPicItem;
import com.chinamobile.wanapp.ui.viewitem.StaggeredItem;
import com.chinamobile.wanapp.ui.viewitem.TitleItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 95470 on 2018/7/30.
 */

public class FindFragment extends BaseFragment {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_find, null);
        ButterKnife.bind(this, mRootView);
        getData();
        setList();
        return mRootView;
    }

    private MultiItemTypeAdapter adapter;
    private List<BaseItem> mDatas;

    private void getData(){
        mDatas = new ArrayList<>();
        for (int i =0;i<12;i++){
            switch (i){
                case 0:{
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_ICON3);
                    mDatas.add(baseItem);
                }
                    break;
                case 1: {
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_TITLE);
                    mDatas.add(baseItem);
                }
                    break;
                case 2: {
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_CARD_BANNER_TWO);
                    mDatas.add(baseItem);
                }
                    break;
                case 3: {
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_TITLE);
                    mDatas.add(baseItem);
                }
                    break;
                case 4: {
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_CARD_BANNER_FIVE);
                    mDatas.add(baseItem);
                }
                    break;
                case 5: {
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_TITLE);
                    mDatas.add(baseItem);
                }
                    break;
                case 6:{
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_BIG_PIC);
                    mDatas.add(baseItem);
                }
                    break;
                case 7:{
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_TITLE);
                    mDatas.add(baseItem);
                }
                    break;
                case 8:{
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_SMALL_PIC);
                    mDatas.add(baseItem);
                }
                    break;
                case 9:{
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_SMALL_PIC);
                    mDatas.add(baseItem);
                }
                break;
                case 10:{
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_SMALL_PIC);
                    mDatas.add(baseItem);
                }
                break;
                case 11:{
                    BaseItem baseItem = new BaseItem();
                    baseItem.setType(BaseItem.ITEM_SMALL_PIC);
                    mDatas.add(baseItem);
                }
                break;
            }


        }
    }

    private void setList(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        //StaggeredGridLayoutManager  manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter = new MultiItemTypeAdapter(getContext(),mDatas);
        adapter.addItemViewDelegate(new StaggeredItem());
        adapter.addItemViewDelegate(new FStaggeredItem());
        adapter.addItemViewDelegate(new TitleItem());
        adapter.addItemViewDelegate(new BigPicItem());
        adapter.addItemViewDelegate(new SmallPicItem());
        adapter.addItemViewDelegate(new Icon3Item());
        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
