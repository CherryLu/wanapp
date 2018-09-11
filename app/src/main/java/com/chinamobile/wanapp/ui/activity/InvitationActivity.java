package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class InvitationActivity extends BaseActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.money_today)
    TextView moneyToday;
    @Bind(R.id.money_yesterday)
    TextView moneyYesterday;
    @Bind(R.id.money_all)
    TextView moneyAll;
    @Bind(R.id.shouyi_layout)
    LinearLayout shouyiLayout;
    @Bind(R.id.qq_share)
    LinearLayout qqShare;
    @Bind(R.id.qqzone_share)
    LinearLayout qqzoneShare;
    @Bind(R.id.weixin_share)
    LinearLayout weixinShare;
    @Bind(R.id.weixinsq_share)
    LinearLayout weixinsqShare;
    @Bind(R.id.sina_share)
    LinearLayout sinaShare;
    @Bind(R.id.layout_share)
    LinearLayout layoutShare;
    @Bind(R.id.invitation_list)
    RecyclerView invitationList;
    @Bind(R.id.invitation_layout)
    LinearLayout invitationLayout;
    @Bind(R.id.layout_rules)
    LinearLayout layoutRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        ButterKnife.bind(this);

        setTitleBar("邀请详情");
    }

    /**
     * 获取邀请人
     */
    private void getData(){
        ApiServiceManager.getUserInviteCase(new HttpResponse() {
            @Override
            public void onNext(ResponseBody body) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }




    @OnClick({R.id.back_image, R.id.qq_share, R.id.qqzone_share, R.id.weixin_share, R.id.weixinsq_share, R.id.sina_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                break;
            case R.id.qq_share:
                break;
            case R.id.qqzone_share:
                break;
            case R.id.weixin_share:
                break;
            case R.id.weixinsq_share:
                break;
            case R.id.sina_share:
                break;
        }
    }
}
