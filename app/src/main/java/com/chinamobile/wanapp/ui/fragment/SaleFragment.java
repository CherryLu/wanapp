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
import com.chinamobile.wanapp.baen.BaseTaskList;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.baen.TitleBean;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.adapter.LeftAdapter;
import com.chinamobile.wanapp.ui.callback.LeftCallBack;
import com.chinamobile.wanapp.ui.viewitem.SmallPicThreelineItem;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

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

    ArrayList<TaskData> dataList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_find_left, null);
        ButterKnife.bind(this, mRootView);
        dataList = (ArrayList<TaskData>) getArguments().getSerializable("LIST");
        swiplayout.setOnRefreshListener(this);
        setLeft();
        getData(0);
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

    private List<TitleBean> titles;
    private List<String> mids;
    private List<TitleBean> getStrings() {
        titles = new ArrayList<>();
        mids = new ArrayList<>();
        for (int i =0;i<dataList.size();i++){
            if ("2".equals(dataList.get(i).getPid())){
                TitleBean titleBean = new TitleBean(dataList.get(i).getMname(),false);
                titles.add(titleBean);
                mids.add(dataList.get(i).getId());
            }
        }
        if (titles.get(0)!=null){
            titles.get(0).setSelect(true);
        }



        return titles;

    }

    private MultiItemTypeAdapter adapter;
    private List<TaskData> mDatas;

    private void getData(int position) {

        ApiServiceManager.getDataList(mids.get(position), new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String  json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList baseTaskList = gson.fromJson(json,BaseTaskList.class);
                    mDatas = new ArrayList<>();
                    mDatas.addAll(baseTaskList.getTaskDatas());
                    setListData();
                    setList();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    private void setListData(){
        if (mDatas!=null){
            for (int i =0;i<mDatas.size();i++){
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

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(100, 1000);
    }

    @Override
    public void leftItemClick(int position) {

        getData(position);


    }
}
