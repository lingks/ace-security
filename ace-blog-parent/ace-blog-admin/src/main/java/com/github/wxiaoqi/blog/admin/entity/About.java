package com.github.wxiaoqi.blog.admin.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by linsheng on 18/2/25.
 */
@Table(name ="about")
public class About {

    @Id
    private String id;
    private String title;
    private String remark;
    private String content;
    private String url;
    private Integer type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
