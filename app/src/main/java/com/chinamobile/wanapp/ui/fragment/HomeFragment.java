package com.chinamobile.wanapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseHomeData;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.HomeBean;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.viewitem.BannerItem;
import com.chinamobile.wanapp.ui.viewitem.Icon4Item;
import com.chinamobile.wanapp.ui.viewitem.RollTextItem;
import com.chinamobile.wanapp.ui.viewitem.TabListItem;
import com.chinamobile.wanapp.ui.viewitem.TopMessageItem;
import com.chinamobile.wanapp.ui.viewitem.TwoCardItem;
import com.chinamobile.wanapp.utils.DefineBAGRefreshWithLoadView;
import com.chinamobile.wanapp.utils.Nagivator;
import com.chinamobile.wanapp.utils.UserManager;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.ResponseBody;

/**
 * Created by 95470 on 2018/7/30.
 */

public class HomeFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Bind(R.id.news_btn)
    Button newsBtn;
    @Bind(R.id.bga)
    BGARefreshLayout bga;
    private DefineBAGRefreshWithLoadView defineBAGRefreshWithLoadView;

    private MultiItemTypeAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_SUCCESS:
                    if (bga!=null){
                        bga.endRefreshing();
                    }
                    setList();
                    break;
                case REFRESH_ERROR:
                    if (bga!=null){
                        bga.endRefreshing();
                    }
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, mRootView);
        setBgaRefreshLayout();
        baseItems = (ArrayList<BaseItem>) getArguments().getSerializable("LIST");
        setList();

        if ("1".equals(UserManager.getInstance().getIfNew())) {
            newsBtn.setVisibility(View.VISIBLE);
        } else {
            newsBtn.setVisibility(View.GONE);
        }
        return mRootView;
    }

    private void setBgaRefreshLayout() {
        defineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(getContext(), false, true);
        bga.setRefreshViewHolder(defineBAGRefreshWithLoadView);
        bga.setDelegate(this);
        defineBAGRefreshWithLoadView.updateLoadingMoreText("加载更多");
    }

    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new MultiItemTypeAdapter(getContext(), baseItems);
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


    @OnClick(R.id.news_btn)
    public void onClick() {
        Nagivator.startRewardActivity(getContext());

    }
    private static final int WAITE_TIME = 500;
    private static final int REFRESH_SUCCESS = 200;
    private static final int REFRESH_ERROR = 201;
    private static final int LOAD_MORE_SUCCESS = 202;
    private static final int LOAD_MORE_ERROR = 203;


    private void getRefresh(){
        ApiServiceManager.getHomeData(UserManager.getInstance().getId(), new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseHomeData data = gson.fromJson(json,BaseHomeData.class);
                    if (data!=null){
                        getListData(data.getHomeBean());
                        if (handler!=null){
                            handler.sendEmptyMessageDelayed(REFRESH_SUCCESS,WAITE_TIME);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    if (handler!=null){
                        handler.sendEmptyMessageDelayed(REFRESH_ERROR,WAITE_TIME);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (handler!=null){
                    handler.sendEmptyMessageDelayed(REFRESH_ERROR,WAITE_TIME);
                }
            }
        });
    }

    private ArrayList<BaseItem> baseItems;
    private void getListData(HomeBean homeBean){
        baseItems = new ArrayList<>();
        if (homeBean==null){
            return;
        }
        for (int i=0;i<6;i++){
            switch (i){
                case 0: {
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_LOGO_MEASSAGE);
                    item.setTopMessage(homeBean.getMbm_res().get(0));
                    baseItems.add(item);
                }
                break;
                case 1: {
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_ICON4);
                    baseItems.add(item);
                }
                break;
                case 2:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_BANNER);
                    item.setDataList(homeBean.getJabm_res());
                    baseItems.add(item);
                }
                break;

                case 3:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_TWO_CARD);
                    baseItems.add(item);
                }
                break;

                case 4:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_ROLL);
                    item.setTopMessage(homeBean.getJanm_res().get(0));
                    baseItems.add(item);

                }
                break;
                case 5:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_TAB_LIST);
                    item.setFirstData(homeBean.getJjp_res());
                    item.setDataList(homeBean.getJamm_res());
                    baseItems.add(item);
                }
                break;

            }
        }
    }



    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getRefresh();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        return false;
    }
}
