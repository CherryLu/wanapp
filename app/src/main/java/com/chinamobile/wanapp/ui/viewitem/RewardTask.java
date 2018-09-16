package com.chinamobile.wanapp.ui.viewitem;

import android.graphics.ImageFormat;
import android.view.View;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.ShareData;
import com.chinamobile.wanapp.baen.Welfare;
import com.chinamobile.wanapp.ui.view.ShareDialog;
import com.chinamobile.wanapp.utils.Nagivator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018/8/12.
 */

public class RewardTask implements ItemViewDelegate<Welfare> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_paihang_task;
    }

    @Override
    public boolean isForViewType(Welfare item, int position) {
        return true;
    }

    @Override
    public void convert(ViewHolder holder, final Welfare welfare, int position) {

        if ((welfare.getJujDc()/welfare.getRequest())>=1){//任务完场
            holder.setText(R.id.completion_btn,"已完成");
        }else {//任务未完成
            holder.setText(R.id.completion_btn,"待完成");
        }

        holder.setText(R.id.maintitle,welfare.getRemark()+"   ("+welfare.getJujEid()+"/"+welfare.getRequest()+")");

        holder.setOnClickListener(R.id.completion_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (welfare.getEid()){
                    case "102":
                        ShareDialog dialog = new ShareDialog(v.getContext());
                        dialog.show();
                        break;
                    case "1":
                        Nagivator.startInvitationActivity(v.getContext());
                        break;
                    case "2":
                        Nagivator.startSortActivity(v.getContext(),1);
                        break;
                    case "3"://注册任务

                        break;
                    case "4":
                        Nagivator.startSortActivity(v.getContext(),2);
                        break;
                    case "5":
                        Nagivator.startSortActivity(v.getContext(),4);
                        break;
                    case "6"://试用任务
                        Nagivator.startSortActivity(v.getContext(),3);
                        break;
                    case "7"://签到
                        Nagivator.startSignActivity(v.getContext());
                        break;
                    case "8"://提现

                        break;
                    case "9"://完成新手任务

                        break;

                    case "10"://完成每日福利

                        break;
                    case "11"://完成周计划

                        break;

                }
                if ("102".equals(welfare.getEid())){

                    //Nagivator.startSortActivity(v.getContext(),4);
                } else if ("2".equals(welfare.getEid())){

                }else if ("5".equals(welfare.getEid())){

                }else if ("1".equals(welfare.getEid())){//邀请好友  跳转邀请页面
                    Nagivator.startInvitationActivity(v.getContext());
                }else if ("4".equals(welfare.getEid())){//好评

                }

            }
        });

    }

}
