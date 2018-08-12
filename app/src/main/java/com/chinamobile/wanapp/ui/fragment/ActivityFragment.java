package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
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
import com.chinamobile.wanapp.ui.viewitem.SmallPicThreelineItem;
import com.chinamobile.wanapp.ui.viewitem.StaggeredItem;
import com.chinamobile.wanapp.ui.viewitem.TitleItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityFragment extends BaseFragment implements OnRefreshListener  {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiplayout)
    SwipeRefreshLayout swiplayout;

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

    private void getData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            BaseItem baseItem = new BaseItem();
            baseItem.setType(BaseItem.ITEM_BIG_PIC);
            mDatas.add(baseItem);
        }
    }

    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MultiItemTypeAdapter(getContext(), mDatas);
        adapter.addItemViewDelegate(new StaggeredItem());
        adapter.addItemViewDelegate(new FStaggeredItem());
        adapter.addItemViewDelegate(new TitleItem());
        adapter.addItemViewDelegate(new BigPicItem());
        adapter.addItemViewDelegate(new SmallPicItem());
        adapter.addItemViewDelegate(new Icon3Item());
        adapter.addItemViewDelegate(new SmallPicThreelineItem());
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

    @Override
    public void onRefresh() {

    }
}
