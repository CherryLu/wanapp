package com.chinamobile.wanapp.baen;

public class TitleBean {
    private String title;
    private boolean isSelect;

    public TitleBean(String title, boolean isSelect) {
        this.title = title;
        this.isSelect = isSelect;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSelect() {
        return isSelect;
    }



}
