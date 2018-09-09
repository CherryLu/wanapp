package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.BaseTaskList;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.viewitem.SmallPicThreelineItem;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/9/9.
 */

public class TwoLineListFragment extends BaseFragment {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private String mid;
    private int count = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = View.inflate(getContext(), R.layout.fragment_sale, null);
        ButterKnife.bind(this, mRootView);
        mid = getArguments().getString("MID");
        refreshData();
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });
        refreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }
        });
        return mRootView;
    }


    private MultiItemTypeAdapter adapter;
    private List<TaskData> mDatas;


    private void refreshData() {

        ApiServiceManager.getDataList(mid, 0, new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList baseTaskList = gson.fromJson(json, BaseTaskList.class);
                    mDatas = new ArrayList<>();
                    mDatas.addAll(baseTaskList.getTaskDatas());
                    setListData();
                    setList();
                    count = 1;
                    if (refreshlayout!=null){
                        refreshlayout.finishRefresh(WAITE_TIME);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (refreshlayout!=null){
                        refreshlayout.finishRefresh(WAITE_TIME);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (refreshlayout!=null){
                    refreshlayout.finishRefresh(WAITE_TIME);
                }
            }
        });
    }

    private void loadMoreData(){
        ApiServiceManager.getDataList(mid, count, new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList baseTaskList = gson.fromJson(json, BaseTaskList.class);
                    if (mDatas==null){
                        mDatas = new ArrayList<>();
                    }
                    mDatas.addAll(baseTaskList.getTaskDatas());
                    setListData();
                    adapter.notifyDataSetChanged();
                    count++;

                    if (refreshlayout!=null){
                        refreshlayout.finishLoadMore(WAITE_TIME);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (refreshlayout!=null){
                        refreshlayout.finishLoadMore(WAITE_TIME);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                if (refreshlayout!=null){
                    refreshlayout.finishLoadMore(WAITE_TIME);
                }
            }
        });
    }

    private void setListData() {
        if (mDatas != null) {
            for (int i = 0; i < mDatas.size(); i++) {
                mDatas.get(i).setType(BaseItem.ITEM_SMALL_PIC_THREE);
            }
        }
    }

    private void setList() {

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new MultiItemTypeAdapter(getContext(), mDatas);
        adapter.addItemViewDelegate(new SmallPicThreelineItem());
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
}
