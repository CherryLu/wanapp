package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.viewitem.RewardTask;
import com.chinamobile.wanapp.utils.Nagivator;
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

    private MultiItemTypeAdapter adapter;
    private List<BaseItem> mDatas;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("TYPE",0);
        if (type==0){
            setTitleBar("每日任务");
        }else {
            setTitleBar("新手任务");
        }

        getData();
        setList();
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
        if (type==0){
            ApiServiceManager.getEveryDay(new HttpResponse() {
                @Override
                public void onNext(ResponseBody body) {
                    try {
                        String json = new String(body.bytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }
        mDatas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            BaseItem baseItem = new BaseItem();
            baseItem.setType(BaseItem.ITEM_REWARD_LIST);
            mDatas.add(baseItem);


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
