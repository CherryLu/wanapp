package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.ui.viewitem.SmallPicThreelineItem;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeSaleActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    swipelayout.setRefreshing(false);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wesale);
        ButterKnife.bind(this);
        setTitleBar("微商购买");
        swipelayout.setOnRefreshListener(this);
        getData();
        setList();
    }


    private MultiItemTypeAdapter adapter;
    private List<TaskData> mDatas;


    private void getData(){
        mDatas = new ArrayList<>();

        for (int i=0;i<10;i++){
            TaskData taskData = new TaskData();
            taskData.setType(BaseItem.ITEM_SMALL_PIC_THREE);
            mDatas.add(taskData);
        }
    }


    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new MultiItemTypeAdapter(this, mDatas);
        adapter.addItemViewDelegate(new SmallPicThreelineItem());
        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);

        recycleview.setLayoutManager(manager);
        recycleview.setAdapter(wrapper);


    }



    @OnClick(R.id.back_image)
    public void onClick() {
        Nagivator.finishActivity(this);
    }

    @Override
    public void onRefresh() {

        if (handler!=null){
            handler.sendEmptyMessageDelayed(200,1000);
        }

    }
}
