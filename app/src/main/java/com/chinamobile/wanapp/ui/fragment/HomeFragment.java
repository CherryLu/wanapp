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
import com.chinamobile.wanapp.ui.viewitem.BannerItem;
import com.chinamobile.wanapp.ui.viewitem.Icon4Item;
import com.chinamobile.wanapp.ui.viewitem.RollTextItem;
import com.chinamobile.wanapp.ui.viewitem.SmallPicItem;
import com.chinamobile.wanapp.ui.viewitem.TabListItem;
import com.chinamobile.wanapp.ui.viewitem.TopMessageItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 95470 on 2018/7/30.
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    private RecyclerView recyclerView;
    private MultiItemTypeAdapter adapter;
    private List<BaseItem> mDatas;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, mRootView);
        getData();
        setList();
        return mRootView;
    }

    private void setList(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new MultiItemTypeAdapter(getContext(),mDatas);
        adapter.addItemViewDelegate(new BannerItem());
        adapter.addItemViewDelegate(new Icon4Item());
        adapter.addItemViewDelegate(new RollTextItem());
        adapter.addItemViewDelegate(new TopMessageItem());
        adapter.addItemViewDelegate(new TabListItem());
        adapter.addItemViewDelegate(new SmallPicItem());
        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    private void getData(){
        mDatas = new ArrayList<>();
        for (int i =0;i<5;i++){
            BaseItem baseItem = new BaseItem();
            baseItem.setType(i);
            mDatas.add(baseItem);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
