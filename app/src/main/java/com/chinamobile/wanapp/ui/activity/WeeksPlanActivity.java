package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseTaskComplet;
import com.chinamobile.wanapp.baen.TaskComplet;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

import static com.chinamobile.wanapp.R.id.position;

public class WeeksPlanActivity extends BaseActivity {


    @Bind(R.id.layout_1_pic)
    ImageView layout1Pic;
    @Bind(R.id.layout_1_btn)
    TextView layout1Btn;
    @Bind(R.id.layout_1)
    LinearLayout layout1;
    @Bind(R.id.layout_2_pic)
    ImageView layout2Pic;
    @Bind(R.id.layout_2_btn)
    TextView layout2Btn;
    @Bind(R.id.layout_2)
    LinearLayout layout2;
    @Bind(R.id.layout_3_pic)
    ImageView layout3Pic;
    @Bind(R.id.layout_3_btn)
    TextView layout3Btn;
    @Bind(R.id.layout_3)
    LinearLayout layout3;
    @Bind(R.id.layout_4_pic)
    ImageView layout4Pic;
    @Bind(R.id.layout_4_btn)
    TextView layout4Btn;
    @Bind(R.id.layout_4)
    LinearLayout layout4;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.layout)
    LinearLayout layout;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.layout_5_pic)
    ImageView layout5Pic;
    @Bind(R.id.layout_5_btn)
    TextView layout5Btn;
    @Bind(R.id.layout_5)
    LinearLayout layout5;
    @Bind(R.id.layout_6_pic)
    ImageView layout6Pic;
    @Bind(R.id.layout_6_btn)
    TextView layout6Btn;
    @Bind(R.id.layout_6)
    LinearLayout layout6;
    @Bind(R.id.layout_7_pic)
    ImageView layout7Pic;
    @Bind(R.id.layout_7_btn)
    TextView layout7Btn;
    @Bind(R.id.layout_7)
    LinearLayout layout7;
    @Bind(R.id.layoutt)
    LinearLayout layoutt;
    @Bind(R.id.line22)
    View line22;


    private LinearLayout[] layouts;

    private ImageView[] imageViews;

    private TextView[] textViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everday);
        ButterKnife.bind(this);
        layouts = new LinearLayout[]{layout1, layout2, layout3, layout4, layout5, layout6, layout7};
        imageViews = new ImageView[]{layout1Pic,layout2Pic,layout3Pic,layout4Pic,layout5Pic,layout6Pic,layout7Pic};
        textViews = new TextView[]{layout1Btn,layout2Btn,layout3Btn,layout4Btn,layout5Btn,layout6Btn,layout7Btn};
        getData();

    }

    private List<TaskComplet> taskComplets = new ArrayList<>();

    private void getData() {
        ApiServiceManager.getTaskCompletionDetail("10", new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = new String(body.bytes());
                    Gson gson = new Gson();
                    BaseTaskComplet baseTaskComplet = gson.fromJson(json, BaseTaskComplet.class);
                    if (baseTaskComplet != null) {
                        taskComplets.addAll(baseTaskComplet.getTaskComplets());
                    }

                    setWeeks();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    private void setWeeks() {
        for (int i = 0; i < taskComplets.size(); i++) {
          TaskComplet complet = taskComplets.get(i);
            if ("1".equals(complet.getStatus())) {
                textViews[complet.getWeekday()].setText("领取");
            }else if("2".equals(complet.getStatus())){
                textViews[complet.getWeekday()].setText("已领取");
            } else if ("0".equals(complet.getStatus())){
                textViews[complet.getWeekday()].setText("未完成");
            }
        }
    }


    private void getTaskComplete(TaskComplet taskComplet){

            TaskComplet complet = taskComplet;
            ApiServiceManager.getTaskCompletion(complet.getJid(), complet.getJzGain(), complet.getRemark(), "", complet.getStatus(), complet.getEid(), new HttpResponse() {
                @Override
                public void onNext(ResponseBody body) {
                    try {
                        String json = new String(body.bytes());
                        JSONObject object = new JSONObject(json);
                        JSONObject object1 = (JSONObject) object.get("userData");
                        String str = object1.getString("flag");
                        if ("Success".equals(str)){
                        textViews[position].setText("已领取");
                        }else {
                            Toast.makeText(WeeksPlanActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(WeeksPlanActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(WeeksPlanActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onError(Throwable e) {
                    Toast.makeText(WeeksPlanActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
                }
            });
    }


    private TaskComplet getPosition(int position){
        TaskComplet complet = null;
        for (int i =0;i<taskComplets.size();i++){
            if (position ==taskComplets.get(i).getWeekday()){
                complet = taskComplets.get(i);
                break;
            }

        }
        return complet;
    }



    @OnClick({R.id.layout_1_pic, R.id.layout_1, R.id.layout_2, R.id.layout_3, R.id.layout_4, R.id.layout_5, R.id.layout_6, R.id.layout_7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_1_pic:
                break;
            case R.id.layout_1: {

                TaskComplet complet = getPosition(1);
                if (complet != null) {
                    getTaskComplete(complet);
                }

            }
                break;
            case R.id.layout_2: {
                TaskComplet complet = getPosition(2);
                if (complet != null) {
                    getTaskComplete(complet);
                }
            }
                break;
            case R.id.layout_3:{
                TaskComplet complet = getPosition(3);
                if (complet != null) {
                    getTaskComplete(complet);
                }
            }
                break;
            case R.id.layout_4: {
                TaskComplet complet = getPosition(4);
                if (complet != null) {
                    getTaskComplete(complet);
                }
            }
                break;
            case R.id.layout_5: {
                TaskComplet complet = getPosition(5);
                if (complet != null) {
                    getTaskComplete(complet);
                }
            }
                break;
            case R.id.layout_6: {
                TaskComplet complet = getPosition(6);
                if (complet != null) {
                    getTaskComplete(complet);
                }
            }
                break;
            case R.id.layout_7: {
                TaskComplet complet = getPosition(7);
                if (complet != null) {
                    getTaskComplete(complet);
                }
            }
                break;
        }
    }
}
