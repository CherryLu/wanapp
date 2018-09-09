package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.chinamobile.wanapp.ui.fragment.BaseFragment;
import com.chinamobile.wanapp.ui.viewitem.RewardTask;
import com.chinamobile.wanapp.utils.Nagivator;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/8/12.
 */

public class RewardActivity extends BaseActivity {


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
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;

    private MultiItemTypeAdapter adapter;
    private List<Welfare> mDatas = new ArrayList<>();

    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("TYPE", 0);
        if (type == 0) {
            setTitleBar("每日任务");
        } else {
            setTitleBar("新手任务");
        }

        refreshlayout.setEnableLoadMore(false);
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        getData();
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
                        setList();
                        if (refreshlayout!=null){
                            refreshlayout.finishRefresh(BaseFragment.WAITE_TIME);
                        }
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

}
