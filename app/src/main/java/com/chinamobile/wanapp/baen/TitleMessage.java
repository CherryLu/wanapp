package com.chinamobile.wanapp.baen;

public class TitleMessage {

    private String title;
    private String mid;
    private int currentPage = 1;


    public TitleMessage() {
    }

    public String getMid() {
        return mid;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
