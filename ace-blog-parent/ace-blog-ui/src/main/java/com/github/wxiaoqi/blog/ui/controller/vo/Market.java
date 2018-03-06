package com.github.wxiaoqi.blog.ui.controller.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */
public class Market implements Serializable {
    private String id;
    private String name;
    private String zhName;
    private String symbol;
    private double marketCap;
    private double change1d;
    private double price;
    private String available_supply;
    private String volume_ex;
    private double change7d;
    private double change1h;
    private Integer sort;
    private long listingTime;

    public double getChange1h() {
        return change1h;
    }

    public void setChange1h(double change1h) {
        this.change1h = change1h;
    }

    public long getListingTime() {
        return listingTime;
    }

    public void setListingTime(long listingTime) {
        this.listingTime = listingTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getChange1d() {
        return change1d;
    }

    public void setChange1d(double change1d) {
        this.change1d = change1d;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getVolume_ex() {
        return volume_ex;
    }

    public void setVolume_ex(String volume_ex) {
        this.volume_ex = volume_ex;
    }

    public double getChange7d() {
        return change7d;
    }

    public void setChange7d(double change7d) {
        this.change7d = change7d;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", zhName='" + zhName + '\'' +
                ", symbol='" + symbol + '\'' +
                ", marketCap=" + marketCap +
                ", change1d=" + change1d +
                ", price=" + price +
                ", available_supply='" + available_supply + '\'' +
                ", volume_ex='" + volume_ex + '\'' +
                ", change7d=" + change7d +
                ", change1h=" + change1h +
                ", sort=" + sort +
                ", listingTime=" + listingTime +
                ", suggest_ex="  +
                '}';
    }
}
