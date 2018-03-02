package com.github.wxiaoqi.blog.admin.util;


/**
 * Created by Administrator on 2018/3/2.
 */
public class MarketResultResponse<T> {

    private int code;
    private T data;
    private String message;
    private int count;

    public MarketResultResponse() {
    }

    public MarketResultResponse(int code, T data, String message,int count) {

        this.code = code;
        this.data = data;
        this.message = message;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
