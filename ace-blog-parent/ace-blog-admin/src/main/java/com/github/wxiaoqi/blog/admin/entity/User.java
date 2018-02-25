package com.github.wxiaoqi.blog.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by linsheng on 18/2/22.
 */
@Table(name = "user")
public class User {

    @Id
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String nick;

    private String sex;

    private String work;

    private String country;

    private String address;

    private String remark;

    private String logo;

    private Date birthy;
    @Transient
    private String tirthyStr;

    private Integer tuijian;

    @Column(name = "page_view")
    private Integer pageView = 0;

    @Column(name = "article_count")
    private Integer articleCount = 0;

    public String getTirthyStr() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(this.getBirthy() != null){
            this.tirthyStr = format.format(this.getBirthy());
        }
        return tirthyStr;
    }

    public void setTirthyStr(String tirthyStr) {
        this.tirthyStr = tirthyStr;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getTuijian() {
        return tuijian;
    }

    public void setTuijian(Integer tuijian) {
        this.tuijian = tuijian;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getBirthy() {
        if(this.tirthyStr != null && !"".equals(this.tirthyStr)){
            System.out.println(tirthyStr);
        }
        return birthy;
    }

    public void setBirthy(Date birthy) {
        this.birthy = birthy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
