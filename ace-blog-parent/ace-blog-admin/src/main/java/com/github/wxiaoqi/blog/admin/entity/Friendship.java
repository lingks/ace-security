package com.github.wxiaoqi.blog.admin.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2018/2/25.
 */
@Table(name = "friendshop")
public class Friendship {
    @Id
    private Integer id;

    private String url;

    private String name;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
