package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.ResponseBody;

public class SaleFragment extends BaseFragment implements LeftCallBack, BGARefreshLayout.BGARefreshLayoutDelegate {


    @Bind(R.id.left_list)
    RecyclerView leftList;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_SUCCESS:

                    break;
                case REFRESH_ERROR:

                    break;
                case LOAD_MORE_SUCCESS:


                    break;
                case LOAD_MORE_ERROR:


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
        setLeft();
       // getData(0);
        getFragment(0);
        return mRootView;
    }

    private LeftAdapter leftAdapter;

    private void setLeft() {
        leftAdapter = new LeftAdapter(getContext(), getStrings());
        leftAdapter.setCallBack(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        leftList.setLayoutManager(manager);
        leftList.setAdapter(leftAdapter);
    }

    private List<TitleBean> titles;
    private List<String> mids;

    private List<TitleBean> getStrings() {
        titles = new ArrayList<>();
        mids = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if ("2".equals(dataList.get(i).getPid())) {
                TitleBean titleBean = new TitleBean(dataList.get(i).getMname(), false);
                titles.add(titleBean);
                mids.add(dataList.get(i).getId());
            }
        }
        if (titles.get(0) != null) {
            titles.get(0).setSelect(true);
        }


        return titles;

    }

    private MultiItemTypeAdapter adapter;
    private List<TaskData> mDatas;

    private int currentPos;

    private void getData(int position) {

        ApiServiceManager.getDataList(mids.get(position), 0, new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList baseTaskList = gson.fromJson(json, BaseTaskList.class);
                    mDatas = new ArrayList<>();
                    mDatas.addAll(baseTaskList.getTaskDatas());
                    setListData();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    private void refreshData(int position) {

        ApiServiceManager.getDataList(mids.get(position), 0, new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList baseTaskList = gson.fromJson(json, BaseTaskList.class);
                    mDatas = new ArrayList<>();
                    mDatas.addAll(baseTaskList.getTaskDatas());
                    setListData();
                    if (handler != null) {
                        handler.sendEmptyMessageDelayed(REFRESH_SUCCESS, WAITE_TIME);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (handler != null) {
                        handler.sendEmptyMessageDelayed(REFRESH_ERROR, WAITE_TIME);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                if (handler != null) {
                    handler.sendEmptyMessageDelayed(REFRESH_ERROR, WAITE_TIME);
                }
            }
        });
    }

    private int currentPage = 1;

    private void loadMoreData(int position) {

        ApiServiceManager.getDataList(mids.get(position), currentPage, new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList baseTaskList = gson.fromJson(json, BaseTaskList.class);
                    mDatas = new ArrayList<>();
                    mDatas.addAll(baseTaskList.getTaskDatas());
                    setListData();
                    //setList();
                    adapter.notifyDataSetChanged();
                    currentPage++;
                    if (handler != null) {
                        handler.sendEmptyMessageDelayed(LOAD_MORE_SUCCESS, WAITE_TIME);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (handler != null) {
                        handler.sendEmptyMessageDelayed(LOAD_MORE_ERROR, WAITE_TIME);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                if (handler != null) {
                    handler.sendEmptyMessageDelayed(LOAD_MORE_ERROR, WAITE_TIME);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void leftItemClick(int position) {
        getFragment(position);

      /*  currentPos = position;
        getData(position);*/
    }

    private void getFragment(int position){
        TwoLineListFragment twoLineListFragment = new TwoLineListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MID",mids.get(position));
        twoLineListFragment.setArguments(bundle);

        FragmentManager manager =  getChildFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragment_container,twoLineListFragment);

        ft.commit();
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refreshData(currentPos);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        loadMoreData(currentPos);
        return true;
    }
}
