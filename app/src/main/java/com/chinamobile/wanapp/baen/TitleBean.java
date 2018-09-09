package com.chinamobile.wanapp.baen;

public class TitleBean {
    private String title;
    private boolean isSelect;

    private int currentPos = 1;

    public TitleBean(String title, boolean isSelect) {
        this.title = title;
        this.isSelect = isSelect;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
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
