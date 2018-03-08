package com.github.wxiaoqi.blog.admin.api.vo;

/**
 * Created by Administrator on 2018/3/8.
 */
public class Buttom {
    private String newsflash_id;
    private String content;
    private String source;
    private String link_title;
    private String link;
    private long issue_time;
    private Integer rank;
    private Integer img_path_type;
    private Integer bull_vote;
    private Integer bad_vote;
    private Integer content_length;

    public String getNewsflash_id() {
        return newsflash_id;
    }

    public void setNewsflash_id(String newsflash_id) {
        this.newsflash_id = newsflash_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink_title() {
        return link_title;
    }

    public void setLink_title(String link_title) {
        this.link_title = link_title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(long issue_time) {
        this.issue_time = issue_time;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getImg_path_type() {
        return img_path_type;
    }

    public void setImg_path_type(Integer img_path_type) {
        this.img_path_type = img_path_type;
    }

    public Integer getBull_vote() {
        return bull_vote;
    }

    public void setBull_vote(Integer bull_vote) {
        this.bull_vote = bull_vote;
    }

    public Integer getBad_vote() {
        return bad_vote;
    }

    public void setBad_vote(Integer bad_vote) {
        this.bad_vote = bad_vote;
    }

    public Integer getContent_length() {
        return content_length;
    }

    public void setContent_length(Integer content_length) {
        this.content_length = content_length;
    }
}
