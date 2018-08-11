package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.ui.viewitem.PersonSortItem;
import com.chinamobile.wanapp.ui.viewitem.RewardTask;
import com.chinamobile.wanapp.utils.Nagivator;
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

public class PersonSortActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

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
    private MultiItemTypeAdapter adapter;
    private List<BaseItem> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouyipaihang);
        ButterKnife.bind(this);
        setTitleBar("收益排行");
        getData();
        setList();
    }


    private void setList(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new MultiItemTypeAdapter(this,mDatas);
        adapter.addItemViewDelegate(new PersonSortItem());

        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    private void getData(){
        mDatas = new ArrayList<>();
        for (int i =0;i<6;i++){
            BaseItem baseItem = new BaseItem();
            baseItem.setType(BaseItem.ITEM_PERSON_LIST);
            mDatas.add(baseItem);


        }
    }




    @OnClick({R.id.back_image, R.id.right_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.right_image:
                break;
        }
    }

    @Override
    public void onRefresh() {

    }
}
