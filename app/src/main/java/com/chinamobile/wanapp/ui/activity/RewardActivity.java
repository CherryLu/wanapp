package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.ui.viewitem.BannerItem;
import com.chinamobile.wanapp.ui.viewitem.Icon4Item;
import com.chinamobile.wanapp.ui.viewitem.RewardTask;
import com.chinamobile.wanapp.ui.viewitem.RollTextItem;
import com.chinamobile.wanapp.ui.viewitem.SmallPicItem;
import com.chinamobile.wanapp.ui.viewitem.TabListItem;
import com.chinamobile.wanapp.ui.viewitem.TopMessageItem;
import com.chinamobile.wanapp.ui.viewitem.TwoCardItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/12.
 */

public class RewardActivity extends BaseActivity {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.get_reward)
    Button getReward;

    private MultiItemTypeAdapter adapter;
    private List<BaseItem> mDatas;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        ButterKnife.bind(this);
        setTitleBar("每日任务");
        getData();
        setList();
    }

    private void setList(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new MultiItemTypeAdapter(this,mDatas);
        adapter.addItemViewDelegate(new RewardTask());

        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    private void getData(){
        mDatas = new ArrayList<>();
        for (int i =0;i<6;i++){
         BaseItem baseItem = new BaseItem();
         baseItem.setType(BaseItem.ITEM_REWARD_LIST);
         mDatas.add(baseItem);


        }
    }

    @OnClick(R.id.get_reward)
    public void onClick() {
    }
}
