package com.chinamobile.wanapp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.BaseTaskList;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.viewitem.SmallPicItem;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by 95470 on 2018/7/28.
 */

public class TitleList extends LinearLayout {

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

    private void inttView(Context context){
        View view = View.inflate(context, R.layout.title_list,this);
        title_area = findViewById(R.id.title_area);
        contain_list = findViewById(R.id.contain_list);
        //setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //addView(view);
    }

    List<String> mids;

    public void setMids(List<String> mids) {
        this.mids = mids;
    }

    public void addTitle(List<String> strings){
        if (title_area!=null){
            title_area.removeAllViews();
        }

        if (strings==null||strings.size()<=0){
            return;
        }

        for (int i =0;i<strings.size();i++){
            final int position = i;
            TextView textView = (TextView) View.inflate(getContext(),R.layout.module_textview,null);
            textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            textView.setText(strings.get(i));
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

        for (int i=0;i< title_area.getChildCount();i++){
            TextView textView = (TextView) title_area.getChildAt(i);
            if (i==position){
                textView.setTextSize(sp2px(50));
            }else {
                textView.setTextSize(sp2px(40));
            }
        }
        String mid = mids.get(position);
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
