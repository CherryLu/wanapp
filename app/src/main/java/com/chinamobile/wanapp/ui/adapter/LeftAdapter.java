package com.chinamobile.wanapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TitleBean;
import com.chinamobile.wanapp.ui.callback.LeftCallBack;

import java.util.List;

/**
 * Created by Administrator on 2018/4/15.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.LViewHolder> {
    private Context context;
    private List<TitleBean> recommends;

    private LeftCallBack callBack;

    public void setCallBack(LeftCallBack callBack) {
        this.callBack = callBack;
    }

    public LeftAdapter(Context context, List<TitleBean> recommends) {
        this.context = context;
        this.recommends = recommends;
    }

    @Override
    public LViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.left_item,null);
        LViewHolder viewHolder = new LViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LViewHolder holder, final int position) {
        final TitleBean bean = recommends.get(position);

        if (bean.isSelect()){
            holder.title.setTextColor(context.getResources().getColor(R.color.color_txt_deep_black));
            holder.re_view.setVisibility(View.VISIBLE);
        }else {
            holder.title.setTextColor(context.getResources().getColor(R.color.gray_text));
            holder.re_view.setVisibility(View.GONE);
        }
        holder.title.setText(recommends.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllFalse();
                bean.setSelect(true);
                notifyDataSetChanged();
                if (callBack!=null){
                    callBack.leftItemClick(position);
                }
            }
        });



    }

    private void setAllFalse(){
        if (recommends==null){
            return;
        }
        for (int i =0;i<recommends.size();i++){
            recommends.get(i).setSelect(false);
        }
    }

    @Override
    public int getItemCount() {
        return recommends==null?0:recommends.size();
    }

    class LViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        View re_view;
        public LViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            re_view = itemView.findViewById(R.id.re_view);
        }

    }
}
