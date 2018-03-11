package com.github.wxiaoqi.blog.admin.api.vo;

/**
 * Created by linsheng on 18/3/10.
 */
public class ArticleInfo {

    private String ID;
    private String Title;
    private String ImgUrl;
    private String Souce;
    private String Author;
    private String AuthorID;
    private String AuthorLogo;
    private String ListImg;
    private Integer ViewType;
    private Integer ArticleType;
    private String Tag;
    private String ShortDescription;
    private Integer commonNum;

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "ID='" + ID + '\'' +
                ", Title='" + Title + '\'' +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", Souce='" + Souce + '\'' +
                ", Author='" + Author + '\'' +
                ", AuthorID='" + AuthorID + '\'' +
                ", AuthorLogo='" + AuthorLogo + '\'' +
                ", ListImg='" + ListImg + '\'' +
                ", ViewType=" + ViewType +
                ", ArticleType=" + ArticleType +
                ", Tag='" + Tag + '\'' +
                ", ShortDescription='" + ShortDescription + '\'' +
                ", commonNum=" + commonNum +
                ", ArticleHref='" + ArticleHref + '\'' +
                ", ViewCount=" + ViewCount +
                '}';
    }

    private String ArticleHref;
    private Integer ViewCount;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getSouce() {
        return Souce;
    }

    public void setSouce(String souce) {
        Souce = souce;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String authorID) {
        AuthorID = authorID;
    }

    public String getAuthorLogo() {
        return AuthorLogo;
    }

    public void setAuthorLogo(String authorLogo) {
        AuthorLogo = authorLogo;
    }

    public String getListImg() {
        return ListImg;
    }

    public void setListImg(String listImg) {
        ListImg = listImg;
    }

    public Integer getViewType() {
        return ViewType;
    }

    public void setViewType(Integer viewType) {
        ViewType = viewType;
    }

    public Integer getArticleType() {
        return ArticleType;
    }

    public void setArticleType(Integer articleType) {
        ArticleType = articleType;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public Integer getCommonNum() {
        return commonNum;
    }

    public void setCommonNum(Integer commonNum) {
        this.commonNum = commonNum;
    }

    public String getArticleHref() {
        return ArticleHref;
    }

    public void setArticleHref(String articleHref) {
        ArticleHref = articleHref;
    }

    public Integer getViewCount() {
        return ViewCount;
    }

    public void setViewCount(Integer viewCount) {
        ViewCount = viewCount;
    }
}
