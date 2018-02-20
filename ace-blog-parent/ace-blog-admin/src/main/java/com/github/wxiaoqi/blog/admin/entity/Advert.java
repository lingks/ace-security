package com.github.wxiaoqi.blog.admin.entity;

/**
 * 战略伙伴
 * Created by linsheng on 18/2/20.
 */

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "advert")
public class Advert {

    @Id
    private Integer id;


    private String logo;

    private String url;

    private Integer sort;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
