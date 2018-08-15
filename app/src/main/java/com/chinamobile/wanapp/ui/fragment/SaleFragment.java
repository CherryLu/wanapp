package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.chinamobile.wanapp.ui.adapter.LeftAdapter;
import com.chinamobile.wanapp.ui.callback.LeftCallBack;
import com.chinamobile.wanapp.ui.viewitem.BigPicItem;
import com.chinamobile.wanapp.ui.viewitem.FStaggeredItem;
import com.chinamobile.wanapp.ui.viewitem.Icon3Item;
import com.chinamobile.wanapp.ui.viewitem.SmallPicThreelineItem;
import com.chinamobile.wanapp.ui.viewitem.StaggeredItem;
import com.chinamobile.wanapp.ui.viewitem.TitleItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SaleFragment extends BaseFragment implements OnRefreshListener,LeftCallBack {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiplayout)
    SwipeRefreshLayout swiplayout;
    @Bind(R.id.left_list)
    RecyclerView leftList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    if (swiplayout != null) {
                        swiplayout.setRefreshing(false);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_find_left, null);
        ButterKnife.bind(this, mRootView);
        swiplayout.setOnRefreshListener(this);
        setLeft();
        getData();
        setList();
        return mRootView;
    }

    private LeftAdapter leftAdapter;

    private void setLeft() {
        leftAdapter = new LeftAdapter(getContext(), getStrings());
        leftAdapter.setCallBack(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        leftList.setLayoutManager(manager);
        leftList.setAdapter(leftAdapter);
    }


    private List<String> getStrings() {
        List<String> strings = new ArrayList<>();
        strings.add("母婴");
        strings.add("家居");
        strings.add("饰品");
        strings.add("实物");
        strings.add("母婴");

        return strings;

    }

    private MultiItemTypeAdapter adapter;
    private List<BaseItem> mDatas;

    private void getData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            BaseItem baseItem = new BaseItem();
            baseItem.setType(BaseItem.ITEM_SMALL_PIC_THREE);
            mDatas.add(baseItem);
        }
    }

    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new MultiItemTypeAdapter(getContext(), mDatas);
        adapter.addItemViewDelegate(new StaggeredItem());
        adapter.addItemViewDelegate(new FStaggeredItem());
        adapter.addItemViewDelegate(new TitleItem());
        adapter.addItemViewDelegate(new BigPicItem());
        adapter.addItemViewDelegate(new Icon3Item());
        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(wrapper);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(100, 1000);
    }

    @Override
    public void leftItemClick() {

    }
}
