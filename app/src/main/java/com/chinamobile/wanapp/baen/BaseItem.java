package com.chinamobile.wanapp.baen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/7/25.
 */

public class BaseItem {

    public static int ITEM_LOGO_MEASSAGE = 0;//logo+消息按钮
    public static int ITEM_ICON4 = 1;//4个icon
    public static int ITEM_BANNER = 2;//banner
    public static int ITEM_ROLL = 3;//rolltxt;
    public static int ITEM_TAB_LIST = 4;//tab栏
    public static int ITEM_SMALL_PIC = 5;//小图
    public static int ITEM_CARD_BANNER_TWO = 6;//可滑动页面滑动
    public static int ITEM_CARD_BANNER_FIVE = 7;//可滑动页面滑动
    public static int ITEM_TITLE = 8;//title
    public static int ITEM_CARD_TWO = 9;//两个可见可滑动
    public static int ITEM_CARD_FIVE = 10;//5个可见可滑动
    public static int ITEM_ICON3 = 11;//4个icon
    public static int ITEM_BIG_PIC = 12;//大图
    public static int ITEM_SMALL_PIC_THREE= 13;//小图 三行数据
    public static int ITEM_TWO_CARD= 14;//小图 三行数据
    public static int ITEM_REWARD_LIST= 15;//每日任务
    public static int ITEM_PERSON_LIST= 13;//每日任务
    private int type; //视图类型 0

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



}
