package com.github.wxiaoqi.blog.admin.entity;

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
    private Integer sort;
    private List<SuggestEx> suggest_ex;

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

    public List<SuggestEx> getSuggest_ex() {
        return suggest_ex;
    }

    public void setSuggest_ex(List<SuggestEx> suggest_ex) {
        this.suggest_ex = suggest_ex;
    }
}

class SuggestEx{
    private String zh_name;
    private String link;
    private String name;
    private String display_name;

    public String getZh_name() {
        return zh_name;
    }

    public void setZh_name(String zh_name) {
        this.zh_name = zh_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}