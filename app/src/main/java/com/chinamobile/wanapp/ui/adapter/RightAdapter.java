package com.chinamobile.wanapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.wanapp.APP;
import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.utils.GlideUtil;
import com.chinamobile.wanapp.utils.Nagivator;

import java.util.List;

/**
 * Created by Administrator on 2018/9/9.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.RViewHolder>{

    private Context context;

    private List<TaskData> taskDatas;


    public RightAdapter(Context context, List<TaskData> taskDatas) {
        this.context = context;
        this.taskDatas = taskDatas;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_small_pic_three,null);
        RViewHolder viewHolder = new RViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        TaskData taskData = taskDatas.get(position);
        holder.maintitle.setText(taskData.getTitle());
        if (taskData.getJobTags()!=null){
            holder.lable1.setText(taskData.getJobTags().split(";")[0]);
            holder.lable2.setText(taskData.getJobTags().split(";")[1]);
        }

        holder.fanxian.setText(taskData.getJzGain()+"%");

        GlideUtil.loadImageView(APP.getContext(),taskData.getIconUrl(),holder.item_pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nagivator.startTaskOClick(v.getContext(),taskData);
                Nagivator.startH5TaskShareActivity(v.getContext());
                // Nagivator.startTaskOClick(v.getContext(),taskData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskDatas==null?0:taskDatas.size();
    }

    class RViewHolder extends RecyclerView.ViewHolder{
      public  TextView maintitle,lable1,lable2,fanxian;
      public  ImageView item_pic;

        public RViewHolder(View itemView) {
            super(itemView);
            maintitle = itemView.findViewById(R.id.maintitle);
            lable1 = itemView.findViewById(R.id.lable1);
            lable2 = itemView.findViewById(R.id.lable2);
            fanxian = itemView.findViewById(R.id.fanxian);
            item_pic = itemView.findViewById(R.id.item_pic);
        }
    }
}
