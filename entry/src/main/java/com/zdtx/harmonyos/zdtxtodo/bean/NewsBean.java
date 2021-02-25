package com.zdtx.harmonyos.zdtxtodo.bean;

public class NewsBean {
    public String title;
    public String dateTime;
    public String count;
    public boolean top;

    public NewsBean(String title, String dateTime, String count, boolean top) {
        this.title = title;
        this.dateTime = dateTime;
        this.count = count;
        this.top = top;
    }
}
