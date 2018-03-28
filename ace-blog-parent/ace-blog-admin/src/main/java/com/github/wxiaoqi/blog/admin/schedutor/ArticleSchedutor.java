package com.github.wxiaoqi.blog.admin.schedutor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.wxiaoqi.blog.admin.api.vo.ArticleData;
import com.github.wxiaoqi.blog.admin.api.vo.ArticleInfo;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.mapper.ArticleMapper;
import com.github.wxiaoqi.blog.admin.util.HttpClientUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by linsheng on 18/3/10.
 */
@EnableScheduling
@Component
public class ArticleSchedutor {

    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 每隔5秒执行, 单位：ms。
     */
    @Scheduled(fixedRate = 5000)
    public void testFixRate() {

        System.out.println("我每隔5秒冒泡一次：" + new Date());
    }

//    public static void main(String[] args) throws Exception{
//        String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=10";
//        String s = HttpClientUtil.doPost(url);
//        JSONObject object = JSONObject.parseObject(s);
//        System.out.println(object);
//        //data1 =
//        String url2 = "https://ihuoqiu.com/Content/information?data=V30M__2F0P3bMkxR__2BatItrulw__2C__2C";
//        Document doc = Jsoup.connect(url2).get();
//        System.out.println(doc);
//        Elements divs = doc.select("div .hq_information_content");
//        for(Element e : divs){
//            System.out.println(e.toString());
//        }
//    }
    @Scheduled(cron = "0/35 * * * * ?")
    public void cron() throws Exception{

        for(int i = 1 ; i < 100; i ++){
            {

                //String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
                String url = "https://ihuoqiu.com/Home/information?data=o7LQwEijG9jcNo0oIK1Q1A__2C__2C&pageIndex=" + i;

                String s = HttpClientUtil.doPost(url);
                JSONObject object = JSONObject.parseObject(s);
                if(object.getString("code").equals("200") && object.getBoolean("success") == true){
                    String msg = object.getString("msg");
                    List<ArticleData> list = JSONArray.parseArray(msg, ArticleData.class);

                    for(ArticleData data : list){

                        if(check(data.getArticleInfo().getTitle())){
                            ArticleInfo info = data.getArticleInfo();
                            Article article = new Article();
                            article.setCrtTime(new Date());
                            article.setUpdTime(new Date());
                            article.setStatus(2);
                            article.setArticleType(1);
                            article.setCover(info.getImgUrl());
                            article.setTitle(info.getTitle());
                            article.setTag(info.getTag());
                            article.setPageView(info.getViewCount());
                            article.setRemark(info.getShortDescription());
                            article.setHotValue(new Random().nextInt(100));
                            article.setSource(info.getSouce());
                            if(data.getData1() != null && !"".equals(data.getData1())){
                                String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                                Document doc = Jsoup.connect(url2).get();
                                Elements divs = doc.select("div .aticle");
                                if(divs.size() > 0) {
                                    for (Element element : divs) {
                                        // System.out.println(element.toString());
                                        article.setContent(element.toString());
                                        System.out.println(element.toString());
                                    }
                                }else{
                                    divs = doc.select("div .hq_information_content");
                                    for (Element element : divs) {
                                        // System.out.println(element.toString());
                                        article.setContent(element.toString());
                                        System.out.println(element.toString());

                                    }
                                }

                                articleMapper.insert(article);
                            }
                        }else{
                            ArticleInfo info = articleMapper.selectByTitle(data.getArticleInfo().getTitle());
                            Article article = new Article();
                            article.setId(Integer.parseInt(info.getID()));
                            article.setCrtTime(new Date());
                            article.setUpdTime(new Date());
                            article.setStatus(2);
                            article.setArticleType(1);
                            article.setCover(info.getImgUrl());
                            article.setTitle(info.getTitle());
                            article.setCover(data.getArticleInfo().getImgUrl());
                            article.setTag(info.getTag());
                            article.setPageView(info.getViewCount());
                            article.setRemark(info.getShortDescription());
                            article.setHotValue(new Random().nextInt(100));
                            article.setSource(data.getArticleInfo().getSouce());
                            if(data.getData1() != null && !"".equals(data.getData1())){
                                String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                                Document doc = Jsoup.connect(url2).get();
                                Elements divs = doc.select("div .aticle");
                                if(divs.size() > 0) {
                                    for (Element element : divs) {
                                        // System.out.println(element.toString());
                                        article.setContent(element.toString());
                                    }
                                }else{
                                    divs = doc.select("div .hq_information_content");
                                    for (Element element : divs) {
                                        // System.out.println(element.toString());
                                        article.setContent(element.toString());

                                    }
                                }
                                articleMapper.updateByPrimaryKey(article);
                            }
                        }
                    }
                }
            }
        }
    }


    @Scheduled(cron = "0/45 * * * * ?")
    public void cron3() throws Exception{

        for(int i = 1 ; i < 100; i ++){

            //String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
            String url = "https://ihuoqiu.com/Home/information?data=BgZLXDwZ8H336uMaxH6fIA__2C__2C&pageIndex=" + i;
            String s = HttpClientUtil.doPost(url);
            JSONObject object = JSONObject.parseObject(s);
            if(object.getString("code").equals("200") && object.getBoolean("success") == true){
                String msg = object.getString("msg");
                List<ArticleData> list = JSONArray.parseArray(msg, ArticleData.class);

                for(ArticleData data : list){

                    if(check(data.getArticleInfo().getTitle())){
                        ArticleInfo info = data.getArticleInfo();
                        Article article = new Article();
                        article.setCrtTime(new Date());
                        article.setUpdTime(new Date());
                        article.setStatus(2);
                        article.setArticleType(2);
                        article.setCover(info.getImgUrl());
                        article.setTitle(info.getTitle());
                        article.setTag(info.getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(info.getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        article.setSource(info.getSouce());
                        if(data.getData1() != null && !"".equals(data.getData1())){
                            String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                            Document doc = Jsoup.connect(url2).get();
                            Elements divs = doc.select("div .hq_information_info");
                            if(divs.size() > 0) {
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                    System.out.println(element.toString());
                                }
                            }else{
                                divs = doc.select("div .hq_information_info");
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                    System.out.println(element.toString());

                                }
                            }

                            articleMapper.insert(article);
                        }
                    }else{
                        ArticleInfo info = articleMapper.selectByTitle(data.getArticleInfo().getTitle());
                        Article article = new Article();
                        article.setId(Integer.parseInt(info.getID()));
                        article.setCrtTime(new Date());
                        article.setUpdTime(new Date());
                        article.setStatus(2);
                        article.setArticleType(2);
                        article.setCover(info.getImgUrl());
                        article.setTitle(info.getTitle());
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTag(info.getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(info.getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        article.setSource(data.getArticleInfo().getSouce());
                        if(data.getData1() != null && !"".equals(data.getData1())){
                            String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                            Document doc = Jsoup.connect(url2).get();
                            Elements divs = doc.select("div .hq_information_info");
                            if(divs.size() > 0) {
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                }
                            }else{
                                divs = doc.select("div .hq_information_info");
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());

                                }
                            }
                            articleMapper.updateByPrimaryKey(article);
                        }
                    }
                }
            }
        }
    }

    @Scheduled(cron = "0/55 * * * * ?")
    public void cron2() throws Exception{

        for(int i = 1 ; i < 100; i ++){

            //String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
            String url = "https://ihuoqiu.com/Home/information?data=GSw__2BNreotKJB2cXvF11nHQ__2C__2C&pageIndex=" + i;
            String s = HttpClientUtil.doPost(url);
            JSONObject object = JSONObject.parseObject(s);
            if(object.getString("code").equals("200") && object.getBoolean("success") == true){
                String msg = object.getString("msg");
                List<ArticleData> list = JSONArray.parseArray(msg, ArticleData.class);

                for(ArticleData data : list){

                    if(check(data.getArticleInfo().getTitle())){
                        ArticleInfo info = data.getArticleInfo();
                        Article article = new Article();
                        article.setCrtTime(new Date());
                        article.setUpdTime(new Date());
                        article.setStatus(2);
                        article.setArticleType(3);
                        article.setCover(info.getImgUrl());
                        article.setTitle(info.getTitle());
                        article.setTag(info.getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(info.getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        article.setSource(info.getSouce());
                        if(data.getData1() != null && !"".equals(data.getData1())){
                            String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                            Document doc = Jsoup.connect(url2).get();
                            Elements divs = doc.select("div .aticle");
                            if(divs.size() > 0) {
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                    System.out.println(element.toString());
                                }
                            }else{
                                divs = doc.select("div .hq_information_content");
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                    System.out.println(element.toString());

                                }
                            }

                            articleMapper.insert(article);
                        }
                    }else{
                        ArticleInfo info = articleMapper.selectByTitle(data.getArticleInfo().getTitle());
                        Article article = new Article();
                        article.setId(Integer.parseInt(info.getID()));
                        article.setCrtTime(new Date());
                        article.setUpdTime(new Date());
                        article.setStatus(2);
                        article.setArticleType(3);
                        article.setCover(info.getImgUrl());
                        article.setTitle(info.getTitle());
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTag(info.getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(info.getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        article.setSource(data.getArticleInfo().getSouce());
                        if(data.getData1() != null && !"".equals(data.getData1())){
                            String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                            Document doc = Jsoup.connect(url2).get();
                            Elements divs = doc.select("div .aticle");
                            if(divs.size() > 0) {
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                }
                            }else{
                                divs = doc.select("div .hq_information_content");
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());

                                }
                            }
                            articleMapper.updateByPrimaryKey(article);
                        }
                    }
                }
            }
        }
    }
    public boolean check(String title){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }

        int count = articleMapper.selectCountByExample(example);
        if(count > 0){
            return false;
        }
        return true;
    }
}
