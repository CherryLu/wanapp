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
import com.chinamobile.wanapp.ui.viewitem.BannerItem;
import com.chinamobile.wanapp.ui.viewitem.Icon4Item;
import com.chinamobile.wanapp.ui.viewitem.RollTextItem;
import com.chinamobile.wanapp.ui.viewitem.SmallPicItem;
import com.chinamobile.wanapp.ui.viewitem.TabListItem;
import com.chinamobile.wanapp.ui.viewitem.TopMessageItem;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 95470 on 2018/7/28.
 */

public class TitleList extends LinearLayout {

    private HashMap<Integer,List<BaseItem>> listHashMap;
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

        setList(getData());

    }

    private List<BaseItem> getData(){
        List<BaseItem> baseItems = new ArrayList<>();
        for (int i =0;i<5;i++){
            BaseItem item = new BaseItem();
            item.setType(5);
            baseItems.add(item);
        }

        return baseItems;
    }

    private void addListData(HashMap<Integer,List<BaseItem>> listHashMap){
        this.listHashMap = listHashMap;
    }

    private void setList(int position){
        if (listHashMap!=null){
            List<BaseItem> items = listHashMap.get(position);

        }
    }
    MultiItemTypeAdapter adapter;

    private void setList(List<BaseItem> mDatas){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new MultiItemTypeAdapter(getContext(),mDatas);
        adapter.addItemViewDelegate(new BannerItem());
        adapter.addItemViewDelegate(new Icon4Item());
        adapter.addItemViewDelegate(new RollTextItem());
        adapter.addItemViewDelegate(new TopMessageItem());
        adapter.addItemViewDelegate(new TabListItem());
        adapter.addItemViewDelegate(new SmallPicItem());
        contain_list.setLayoutManager(manager);
        contain_list.setAdapter(adapter);
    }




}
