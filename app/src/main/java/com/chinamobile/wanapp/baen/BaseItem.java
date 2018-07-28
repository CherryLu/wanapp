package com.chinamobile.wanapp.baen;

/**
 * Created by Administrator on 2018/7/25.
 */

public class BaseItem {
    public static int ITEM_LOGO_MEASSAGE = 0;//logo+消息按钮
    public static int ITEM_ICON = 1;//logo+消息按钮
    public static int ITEM_BANNER = 2;//banner
    public static int ITEM_ROLL = 3;//rolltxt;
    public static int ITEM_TAB_LIST = 4;//tab栏

    private int type; //视图类型 0

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
