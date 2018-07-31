package com.chinamobile.wanapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.ui.fragment.FindFragment;
import com.chinamobile.wanapp.ui.fragment.HomeFragment;
import com.chinamobile.wanapp.ui.fragment.MineFragment;

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
    private FindFragment findFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tabCheck(0);
      //  parseM3U8();

    }


    /*private void parseM3U8(){
        String url = "http://audio2.china-plus.net:31080/10.102.62.10/radios/104992/segment20180731161309-000564.aac";

        URL uri = null;
        try {
        uri = new URL(url);
        MediaPlayer player = new MediaPlayer();
        player.setDataSource(url);
            player.prepare();
        player.start();
       *//* HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
        InputStream inputStream = connection.getInputStream();
        PlaylistParser parser = new PlaylistParser(inputStream, Format.EXT_M3U, Encoding.UTF_8, ParsingMode.LENIENT);
        Playlist playlist = null;*//*
*//*
            playlist = parser.parse();
            if (playlist.hasMasterPlaylist() && playlist.getMasterPlaylist().hasUnknownTags()) {
                Log.e("ZX",playlist.getMasterPlaylist().getUnknownTags()+"");
            } else if (playlist.hasMediaPlaylist() && playlist.getMediaPlaylist().hasUnknownTags()) {
                Log.e("ZX",playlist.getMediaPlaylist().getUnknownTags()+"");
            } else {
                Log.e("ZX","Parsing without unknown tags successful"+"");
            }*//*

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

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
                    transaction.add(R.id.container,homeFragment,"home");
                }
                transaction.show(homeFragment);
                break;
            case 1:
                tabText2.setTextColor(getResources().getColor(R.color.blue_color));
                if (findFragment==null){
                    findFragment = new FindFragment();
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
