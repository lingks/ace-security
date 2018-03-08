package com.github.wxiaoqi.blog.admin.api.vo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */
public class FlashData {
    private String  date;
    private List<String> top;
    private List<Buttom> buttom;

    @Override
    public String toString() {
        return "FlashData{" +
                "date='" + date + '\'' +
                ", top='" + top + '\'' +
                ", buttom=" + buttom +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTop() {
        return top;
    }

    public void setTop(List<String> top) {
        this.top = top;
    }

    public List<Buttom> getButtom() {
        return buttom;
    }

    public void setButtom(List<Buttom> buttom) {
        this.buttom = buttom;
    }
}
