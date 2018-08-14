package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.chinamobile.wanapp.ui.viewitem.TabListItem;
import com.chinamobile.wanapp.ui.viewitem.TopMessageItem;
import com.chinamobile.wanapp.ui.viewitem.TwoCardItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 95470 on 2018/7/30.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener  {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    SwipeRefreshLayout refreshLayout;

    private MultiItemTypeAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    refreshLayout.setRefreshing(false);
                    break;
            }
            super.handleMessage(msg);
        }
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ArrayList<BaseItem> baseItems;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, null);
        refreshLayout = (SwipeRefreshLayout) mRootView;
        refreshLayout.setOnRefreshListener(this);
        ButterKnife.bind(this, mRootView);
        baseItems = (ArrayList<BaseItem>) getArguments().getSerializable("LIST");
        setList();
        return mRootView;
    }

    private void setList(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new MultiItemTypeAdapter(getContext(),baseItems);
        adapter.addItemViewDelegate(new BannerItem());
        adapter.addItemViewDelegate(new Icon4Item());
        adapter.addItemViewDelegate(new RollTextItem());
        adapter.addItemViewDelegate(new TopMessageItem());
        adapter.addItemViewDelegate(new TabListItem());
        adapter.addItemViewDelegate(new TwoCardItem());
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
        if (handler!=null){
            handler.sendEmptyMessageDelayed(0,1000);
        }
    }
}
