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
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.ui.viewitem.SmallPicItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

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

    public void addTitle(List<String> strings){
        if (title_area!=null){
            title_area.removeAllViews();
        }

        if (strings==null||strings.size()<=0){
            return;
        }

        for (int i =0;i<strings.size();i++){
            TextView textView = (TextView) View.inflate(getContext(),R.layout.module_textview,null);
            textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            textView.setText(strings.get(i));

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            title_area.addView(textView);
        }

    }



    private List<TaskData> getData(){
        List<TaskData> baseItems = new ArrayList<>();
        for (int i =0;i<5;i++){
            TaskData item = new TaskData();
            item.setType(5);
            baseItems.add(item);
        }
        return baseItems;
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
        contain_list.setLayoutManager(manager);
        contain_list.setAdapter(adapter);
    }




}
