package com.chinamobile.wanapp.utils;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * BottomNavigationView反射修改工具类
 * Created by Android on 2017/3/20.
 */
public class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView navigationView){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);

        Field shiftingMode = null;
        try {
            shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
