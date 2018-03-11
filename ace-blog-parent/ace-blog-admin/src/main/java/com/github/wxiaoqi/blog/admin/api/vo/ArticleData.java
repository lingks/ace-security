package com.github.wxiaoqi.blog.admin.api.vo;

/**
 * Created by linsheng on 18/3/10.
 */
public class ArticleData {
    private String data1;
    private String data2;
    private ArticleInfo ArticleInfo;

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public com.github.wxiaoqi.blog.admin.api.vo.ArticleInfo getArticleInfo() {
        return ArticleInfo;
    }

    public void setArticleInfo(com.github.wxiaoqi.blog.admin.api.vo.ArticleInfo articleInfo) {
        ArticleInfo = articleInfo;
    }

    @Override
    public String toString() {
        return "ArticleData{" +
                "data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", ArticleInfo=" + ArticleInfo +
                '}';
    }
}
