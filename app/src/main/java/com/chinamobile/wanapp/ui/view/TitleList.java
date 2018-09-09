package com.chinamobile.wanapp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.APP;
import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.BaseTaskList;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.baen.TitleMessage;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.activity.BaseActivity;
import com.chinamobile.wanapp.ui.fragment.SortFragment;
import com.chinamobile.wanapp.ui.viewitem.SmallPicItem;
import com.chinamobile.wanapp.utils.LogUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.ResponseBody;

/**
 * Created by 95470 on 2018/7/28.
 */

public class TitleList extends LinearLayout  {



    public TitleList(Context context) {
        super(context);
        inttView(context);
    }

    public TitleList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inttView(context);
    }

    public TitleList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inttView(context);
    }

    LinearLayout title_area;
    RecyclerView contain_list;
    SmartRefreshLayout refreshlayout;

    private void inttView(Context context){
        View view = View.inflate(context, R.layout.title_list,this);
        title_area = findViewById(R.id.title_area);
        contain_list = findViewById(R.id.contain_list);



    }



    List<TitleMessage> messages;

    public void setMessages(List<TitleMessage> messages) {
        this.messages = messages;
        APP.currentTitle = messages.get(0);
        addTitle();
    }



    public void addTitle(){
        if (title_area!=null){
            title_area.removeAllViews();
        }
        if (messages==null||messages.size()<=0){
            return;
        }

        for (int i =0;i<messages.size();i++){
            final int position = i;
            TextView textView = (TextView) View.inflate(getContext(),R.layout.module_textview,null);
            textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            textView.setText(messages.get(i).getTitle());
            if (i==0){
                textView.setTextSize(sp2px(50));
            }
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelection(position);
                }
            });
            title_area.addView(textView);
        }
    }

    private void setSelection(int position){
        APP.currentTitle = messages.get(position);
        for (int i=0;i< title_area.getChildCount();i++){
            TextView textView = (TextView) title_area.getChildAt(i);
            if (i==position){
                textView.setTextSize(sp2px(50));
            }else {
                textView.setTextSize(sp2px(40));
            }
        }
        String mid = messages.get(position).getMid();

        ApiServiceManager.getDataList(mid, new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList baseTaskList = gson.fromJson(json,BaseTaskList.class);
                    if (baseTaskList!=null){
                        setDefaultData(baseTaskList.getTaskDatas());
                    }else {
                        setDefaultData(new ArrayList<TaskData>());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    setDefaultData(new ArrayList<TaskData>());
                }
            }

            @Override
            public void onError(Throwable e) {
                setDefaultData(new ArrayList<TaskData>());
            }
        });
    }


    public int sp2px(int spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue / fontScale + 0.5f);

    }


    MultiItemTypeAdapter adapter;

    public void setDefaultData(List<TaskData> mDatas){
        initList(mDatas);
        adapter = new MultiItemTypeAdapter(getContext(),mDatas);
        setList();
    }

    private void initList(List<TaskData> mDatas) {
        if (mDatas==null){
            return;
        }
        for (int i=0;i<mDatas.size();i++){
            mDatas.get(i).setType(BaseItem.ITEM_SMALL_PIC);
        }
    }

    private void setList(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter.addItemViewDelegate(new SmallPicItem());
        EmptyWrapper wrapper = new EmptyWrapper(adapter);
        wrapper.setEmptyView(R.layout.empty_view);
        contain_list.setLayoutManager(manager);
        contain_list.setAdapter(wrapper);
    }


}
