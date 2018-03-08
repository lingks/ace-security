package com.github.wxiaoqi.blog.admin.api.vo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */
public class FlashVO {

    private String date;

    private List<FlashData> data;

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FlashVO{" +
                "date='" + date + '\'' +
                ", data=" + data +
                '}';
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<FlashData> getData() {
        return data;
    }

    public void setData(List<FlashData> data) {
        this.data = data;
    }
}
