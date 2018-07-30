package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.ui.viewitem.StaggeredItem;
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
        for (int i =0;i<20;i++){
            BaseItem baseItem = new BaseItem();
            baseItem.setType(BaseItem.ITEM_CARD_BANNER);
            mDatas.add(baseItem);
        }
    }

    private void setList(){
        StaggeredGridLayoutManager  manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter = new MultiItemTypeAdapter(getContext(),mDatas);
        adapter.addItemViewDelegate(new StaggeredItem());
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
