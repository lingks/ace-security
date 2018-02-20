package com.github.wxiaoqi.blog.admin.entity;

/**
 * 战略伙伴
 * Created by linsheng on 18/2/20.
 */
import javax.persistence.*;

@Table(name = "strategic_partner")
public class StrategicPartner {

    @Id
    private Integer id;

    private String name;

    private String logo;

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
