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
import com.chinamobile.wanapp.utils.GlideUtil;
import com.chinamobile.wanapp.utils.Nagivator;
import com.chinamobile.wanapp.utils.ScreenUtil;
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


    private View getStepView(int positin, TaskData taskData) {

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


        return view;
    }
}
