package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseBean;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.baen.HomeBean;
import com.chinamobile.wanapp.http.ApiServiceManager;
import com.chinamobile.wanapp.http.HttpResponse;
import com.chinamobile.wanapp.ui.fragment.HomeFragment;
import com.chinamobile.wanapp.ui.fragment.MineFragment;
import com.chinamobile.wanapp.ui.fragment.NewFindFragment;
import com.chinamobile.wanapp.utils.UserManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.root_bottom_home_tab_1)
    RadioButton rootBottomHomeTab1;
    @Bind(R.id.root_bottom_home_tab_2)
    RadioButton rootBottomHomeTab2;
    @Bind(R.id.root_bottom_home_tab_3)
    RadioButton rootBottomHomeTab3;
    @Bind(R.id.root_bottom_tab_layout)
    RadioGroup rootBottomTabLayout;
    @Bind(R.id.container)
    FrameLayout container;

    private HomeFragment homeFragment;
    private NewFindFragment findFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();
    }





    private void getData(){
        ApiServiceManager.getHomeData("", new HttpResponse() {
            @Override
            public void onNext(BaseBean baseItem) {
                if (baseItem!=null){
                    getListData(baseItem.getHomeBean());
                    tabCheck(0);
                }

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private ArrayList<BaseItem> baseItems;
    private void getListData(HomeBean homeBean){
        baseItems = new ArrayList<>();
        if (homeBean==null){
            return;
        }
        for (int i=0;i<6;i++){
            switch (i){
                case 0: {
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_LOGO_MEASSAGE);
                   // item.setTopMessage(homeBean.getMbm_res().get(0));
                    baseItems.add(item);
                }
                    break;
                case 1: {
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_ICON4);
                    baseItems.add(item);
                }
                break;
                case 2:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_BANNER);
                    item.setDataList(homeBean.getJabm_res());
                    baseItems.add(item);
                }
                    break;

                case 3:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_TWO_CARD);
                    baseItems.add(item);
                }
                    break;

                case 4:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_ROLL);
                    item.setTopMessage(homeBean.getJanm_res().get(0));
                    baseItems.add(item);

                }
                break;
                case 5:{
                    BaseItem item = new BaseItem();
                    item.setType(BaseItem.ITEM_TAB_LIST);
                    item.setFirstData(homeBean.getJjp_res());
                    item.setDataList(homeBean.getJamm_res());
                    baseItems.add(item);
                }
                    break;

            }
        }
    }


    private void hideAll(FragmentTransaction transaction){
        if (homeFragment!=null){
            transaction.hide(homeFragment);
        }

        if (findFragment!=null){
            transaction.hide(findFragment);
        }

        if (mineFragment!=null){
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    private void tabCheck(int position){
        TextView tabText1 = (TextView) findViewById(R.id.root_bottom_home_tab_1);
        TextView tabText2 = (TextView) findViewById(R.id.root_bottom_home_tab_2);
        TextView tabText3 = (TextView) findViewById(R.id.root_bottom_home_tab_3);

        tabText1.setTextColor(getResources().getColor(R.color.bottom_tab_txt));
        tabText2.setTextColor(getResources().getColor(R.color.bottom_tab_txt));
        tabText3.setTextColor(getResources().getColor(R.color.bottom_tab_txt));


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAll(transaction);
        switch (position){
            case 0:
                tabText1.setTextColor(getResources().getColor(R.color.blue_color));
                if (homeFragment==null){
                    homeFragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LIST", baseItems);
                    homeFragment.setArguments(bundle);
                    transaction.add(R.id.container,homeFragment,"home");
                }
                transaction.show(homeFragment);
                break;
            case 1:
                tabText2.setTextColor(getResources().getColor(R.color.blue_color));
                if (findFragment==null){
                    findFragment = new NewFindFragment();
                    transaction.add(R.id.container,findFragment,"find");
                }
                transaction.show(findFragment);
                break;
            case 2:
                tabText3.setTextColor(getResources().getColor(R.color.blue_color));
                if (mineFragment==null){
                    mineFragment = new MineFragment();
                    transaction.add(R.id.container,mineFragment,"mine");
                }
                transaction.show(mineFragment);
                break;
        }
        transaction.commit();
    }

    @OnClick({R.id.root_bottom_home_tab_1, R.id.root_bottom_home_tab_2, R.id.root_bottom_home_tab_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root_bottom_home_tab_1:
                tabCheck(0);
                break;
            case R.id.root_bottom_home_tab_2:
                tabCheck(1);
                break;
            case R.id.root_bottom_home_tab_3:
                tabCheck(2);
                break;
        }
    }

}
