package com.chinamobile.wanapp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        View view = View.inflate(context, R.layout.title_list,null);
        title_area = view.findViewById(R.id.title_area);
        contain_list = view.findViewById(R.id.contain_list);
    }

    private void addTitle(List<String> strings){
        if (title_area!=null){
            title_area.removeAllViews();
        }

        if (strings==null||strings.size()<=0){
            return;
        }

        for (int i =0;i<strings.size();i++){
            TextView textView = (TextView) View.inflate(getContext(),R.layout.module_textview,null);
            textView.setText(strings.get(i));

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            title_area.addView(textView);
        }

    }

    private void addListData(HashMap<Integer,List<BaseItem>> listHashMap){
        this.listHashMap = listHashMap;
    }

    private void setList(int position){
        if (listHashMap!=null){
            List<BaseItem> items = listHashMap.get(position);

        }
    }




}
