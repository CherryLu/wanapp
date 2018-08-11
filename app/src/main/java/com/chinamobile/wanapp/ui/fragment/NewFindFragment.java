package com.chinamobile.wanapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinamobile.wanapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewFindFragment extends BaseFragment {


    @Bind(R.id.tab1)
    TextView tab1;
    @Bind(R.id.tab2)
    TextView tab2;
    @Bind(R.id.tab_layout)
    LinearLayout tabLayout;
    @Bind(R.id.container)
    FrameLayout container;

    SaleFragment saleFragment;
    ActivityFragment activityFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void hideAll(FragmentTransaction transaction){
        if (saleFragment!=null){
            transaction.hide(saleFragment);
        }

        if (activityFragment!=null){
            transaction.hide(activityFragment);
        }

    }

    private void setFragment(int which){
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideAll(transaction);
        if (which==0){
            if (saleFragment==null){
                saleFragment = new SaleFragment();
                transaction.add(R.id.container,saleFragment,"sale");
            }
            transaction.show(saleFragment);
        }else {
            if (activityFragment==null){
                activityFragment = new ActivityFragment();
                transaction.add(R.id.container,activityFragment,"activity");
            }
            transaction.show(activityFragment);
        }

        transaction.commit();



    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView= inflater.inflate(R.layout.fragment_new_find, null);
        ButterKnife.bind(this, mRootView);
        setFragment(0);
        return mRootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tab1, R.id.tab2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                setPosition(0);
                setFragment(0);
                break;
            case R.id.tab2:
                setPosition(1);
                setFragment(1);
                break;
        }
    }


    private void setPosition(int position){
        if (position==0){

            tab1.setBackgroundResource(R.drawable.blue_bac_left);
            tab1.setTextColor(Color.WHITE);

            tab2.setBackground(null);
            tab2.setTextColor(Color.parseColor("#2689f7"));

        }else if (position==1){

            tab2.setBackgroundResource(R.drawable.blue_bac_right);
            tab2.setTextColor(Color.WHITE);

            tab1.setBackground(null);
            tab1.setTextColor(Color.parseColor("#2689f7"));

        }
    }
}
