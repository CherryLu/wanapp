package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseWelfare;
import com.chinamobile.wanapp.baen.Welfare;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.viewitem.RewardTask;
import com.chinamobile.wanapp.utils.DefineBAGRefreshWithLoadView;
import com.chinamobile.wanapp.utils.Nagivator;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/8/12.
 */

public class RewardActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.get_reward)
    Button getReward;
    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.bag)
    BGARefreshLayout bag;

    private MultiItemTypeAdapter adapter;
    private List<Welfare> mDatas = new ArrayList<>();

    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("TYPE", 0);
        setBgaRefreshLayout();
        if (type == 0) {
            setTitleBar("每日任务");
        } else {
            setTitleBar("新手任务");
        }

        getData();
    }


    DefineBAGRefreshWithLoadView defineBAGRefreshWithLoadView;

    private void setBgaRefreshLayout() {
        defineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(this, false, true);
        bag.setRefreshViewHolder(defineBAGRefreshWithLoadView);
        bag.setDelegate(this);
        defineBAGRefreshWithLoadView.updateLoadingMoreText("加载更多");
    }

    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new MultiItemTypeAdapter(this, mDatas);
        adapter.addItemViewDelegate(new RewardTask());

        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(wrapper);
    }

    private void getData() {
        if (type == 0) {
            ApiServiceManager.getEveryDay(new HttpResponse() {
                @Override
                public void onNext(ResponseBody body) {
                    try {
                        String json = new String(body.bytes());
                        Gson gson = new Gson();
                        BaseWelfare baseWelfare = gson.fromJson(json, BaseWelfare.class);
                        if (baseWelfare != null) {
                            mDatas.clear();
                            mDatas.addAll(baseWelfare.getWelfares());
                        }
                        if (bag!=null){
                            bag.endRefreshing();
                        }
                        setList();
                    } catch (IOException e) {
                        e.printStackTrace();
                        setList();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    setList();

                }
            });
        }
    }


    @OnClick({R.id.back_image, R.id.get_reward})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.get_reward:
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
