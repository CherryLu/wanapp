package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseTaskList;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.view.ShareDialog;
import com.chinamobile.wanapp.utils.GlideUtil;
import com.chinamobile.wanapp.utils.Nagivator;
import com.chinamobile.wanapp.utils.ScreenUtil;
import com.chinamobile.wanapp.utils.SystemUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/8/1.
 */

public class TaskDetailsActivity extends BaseActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.app_pic)
    ImageView appPic;
    @Bind(R.id.app_name)
    TextView appName;
    @Bind(R.id.app_size)
    TextView appSize;
    @Bind(R.id.app_lable1)
    TextView appLable1;
    @Bind(R.id.app_lable2)
    TextView appLable2;
    @Bind(R.id.steps_layouy)
    LinearLayout stepsLayouy;
    @Bind(R.id.image_layout)
    LinearLayout imageLayout;
    @Bind(R.id.download)
    Button download;
    @Bind(R.id.getmoney)
    TextView getmoney;

    private TaskData taskData;

    private List<TaskData> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_download);
        ButterKnife.bind(this);
        setTitleBar("任务详情");
        taskData = (TaskData) getIntent().getSerializableExtra("TASK");
        if (taskData == null) {
            Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show();
            Nagivator.finishActivity(this);
        }
        getDetails();

    }


    private void getDetails() {


        ApiServiceManager.getTaskDetail(taskData.getId(), new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskList taskList = gson.fromJson(json, BaseTaskList.class);
                    data.addAll(taskList.getTaskDatas());
                    initDta();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    private void initDta() {
        initView();
    }

    private Double price = 0d;
    private void initView() {
        stepsLayouy.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            TaskData taskData = data.get(i);
            price = price+taskData.getJzGain();
            if (taskData.getFid().equals(taskData.getId())) {//父任务
                ininTitleData(taskData);
            }
            stepsLayouy.addView(getStepView(i, data.get(i)));
        }

        getmoney.setText("+"+(price/100)+"元");
    }

    private void ininTitleData(TaskData taskData) {
        GlideUtil.loadImageView(this, taskData.getIconUrl(), appPic);
        appName.setText(taskData.getTitle());
        String[] str = taskData.getJobTags().split(";");

        if (str.length > 0) {
            appLable1.setText(str[0]);
        }
        if (str.length > 1) {
            appLable2.setText(str[1]);
        }
        if (taskData.getSampleimgUrl()!=null){
          String[] strings = taskData.getSampleimgUrl().split(";");
          initLayout(strings);
        }

    }


    private void initLayout(String[] images) {
        imageLayout.removeAllViews();
        for (int i=0;i<images.length;i++ ){
            ImageView imageView = (ImageView) View.inflate(this,R.layout.moddle_image,null);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtil.dip2px(this,200),ScreenUtil.dip2px(this,300),1));
            if (i==0){
                imageView.setPadding(0,0,ScreenUtil.dip2px(this,5),0);
            }else {
                imageView.setPadding(ScreenUtil.dip2px(this,5),0,0,0);
            }

            GlideUtil.loadImageView(this,images[i],imageView);

            imageLayout.addView(imageView);
        }
    }

    @OnClick({R.id.back_image, R.id.download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.download:
                break;
        }
    }


    private View getStepView(int positin, final TaskData taskData) {

        if (taskData == null) {
            return null;
        }
        View view = View.inflate(this, R.layout.step_item, null);
        TextView step_number = view.findViewById(R.id.step_number);
        View line = view.findViewById(R.id.line);
        TextView step_descrip = view.findViewById(R.id.step_descrip);
        TextView step_descripe = view.findViewById(R.id.step_descripe);
        TextView step_money = view.findViewById(R.id.step_money);
        TextView step_completion = view.findViewById(R.id.step_completion);
        step_number.setText(positin + 1 + "");

        step_descrip.setText(taskData.getTitle());
        step_descripe.setText(taskData.getSubtitle());
        step_money.setText("+" + (taskData.getJzGain() / 100) + "元");


        step_completion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Nagivator.startUploadActivity(TaskDetailsActivity.this,taskData);

            }
        });

        return view;
    }



    private void doAction(TaskData taskData){
        if (taskData==null){
            return;
        }
        switch (taskData.getAction()){
            case "1":// 邀请好友   分享
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }

                break;
            case "2"://下载       直接下载

                break;
            case "3"://注册任务   跳转APP
                SystemUtil.openOtherAPP(this,taskData.getJobStr());

                break;

            case "4"://好评任务    跳转截图

                Nagivator.startUploadActivity(this,taskData);


                break;
            case "5"://完成分享任务      分享
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();

            }


                break;
            case "6"://完成试用任务       弹窗奖励

                break;
            case "7"://完成每日签到       弹窗奖励

                break;
            case "8"://完成成功提现       提现确定接口

                break;
            case "9"://完成新手任务       领取新手奖励

                break;
            case "10"://完成每日福利
                break;

            case "11"://完成周计划

                break;
            case "12"://完成晒单任务

                Nagivator.startUploadActivity(this,taskData);

                break;
            case "13"://分享呗有效点击

                break;

            case "101"://APP 分享朋友圈
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "102"://APP 分享微信群或QQ群
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "103"://APP 分享微信群或QQ群(10人以上)
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "104"://APP 分享微信群或QQ群(30人以上)
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "105"://APP 分享微信群或QQ群(50人以上)
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "106"://APP 分享微信群或QQ群(100人以上)
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "107:"://APP 分享微信群或QQ群(100人以上)
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "108:"://APP 分享微信群或QQ群(500人以上)
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;

            case "201:"://百度市场好评
                break;
            case "202:"://华为市场好评
                break;
            case "203:"://小米市场好评
                break;
            case "204:"://vivo市场好评
                break;
            case "205:"://应用宝市场好评
                break;
            case "206:"://安智市场好评
                break;


            case "301:"://好友完成新手任务
                break;
            case "302:"://好友完成提现
                break;
            case "303:"://好友完成邀人
                break;

            case "1001"://任务 分享
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "1002"://任务 分享QQ群或微信群
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "1003"://任务 分享QQ群或微信群（10）
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "1004"://任务 分享QQ群或微信群（30）
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "1005"://任务 分享QQ群或微信群（50）
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "1006"://任务 分享QQ群或微信群（100）
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "1007"://任务 分享QQ群或微信群（200）
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;
            case "1008"://任务 分享QQ群或微信群（500）
            {
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
            }
                break;


        }
    }
}
