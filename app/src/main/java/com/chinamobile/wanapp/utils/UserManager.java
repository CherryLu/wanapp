package com.chinamobile.wanapp.utils;

import com.chinamobile.wanapp.baen.UserBean;

public class UserManager {

    private static UserManager userManager;

    private UserBean userBean;

    private UserManager() {
    }

    /**
     * 单例
     * @return
     */
    public static UserManager  getInstance(){
        if (userManager==null){
            userManager = new UserManager();
        }

        return userManager;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    /**
     * 获取id
     * @return
     */
    public String  getId(){
        if (userBean!=null){
            return userBean.getId();
        }

        return "";
    }

    /**
     * 获取是否新手
     * @return
     */
    public String getIfNew(){
        if (userBean!=null){
            return userBean.getIfnew();
        }

        return "";
    }
}
