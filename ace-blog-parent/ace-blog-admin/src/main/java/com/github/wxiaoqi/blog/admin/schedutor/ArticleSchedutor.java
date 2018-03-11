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

    @Scheduled(cron = "0/35 * * * * ?")
    public void cron() throws Exception{

        for(int i = 1 ; i < 15; i ++){

            String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
            //String url = "https://ihuoqiu.com/Home/information?data=W83Lysi$__2B$bHQTpVifRa$__2B$HDg$_2C$$_2C$&pageIndex=" + i;
            System.out.println("定时任务");
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
                        article.setType(1);
                        article.setArticleType(1);
                        article.setCover(info.getImgUrl());
                        article.setTitle(info.getTitle());
                        article.setTag(info.getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(info.getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                        Document doc = Jsoup.connect(url2).get();
                        Elements divs = doc.select("div .article");
                        for (Element element : divs) {
                            System.out.println(element.toString());
                            article.setContent(element.toString());
                        }

                        articleMapper.insert(article);

                    }
                }
            }
        }
    }


    @Scheduled(cron = "0/45 * * * * ?")
    public void cron3() throws Exception{

        for(int i = 1 ; i < 15; i ++){

            //String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
            String url = "https://ihuoqiu.com/Home/information?data=W83Lysi$__2B$bHQTpVifRa$__2B$HDg$_2C$$_2C$&pageIndex=" + i;
            System.out.println("定时任务");
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
                        article.setType(3);
                        article.setArticleType(3);
                        article.setCover(info.getImgUrl());
                        article.setTitle(info.getTitle());
                        article.setTag(info.getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(info.getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                        Document doc = Jsoup.connect(url2).get();
                        Elements divs = doc.select("div .article");
                        for (Element element : divs) {
                            System.out.println(element.toString());
                            article.setContent(element.toString());
                        }

                        articleMapper.insert(article);

                    }
                }
            }
        }
    }

    @Scheduled(cron = "0/55 * * * * ?")
    public void cron2() throws Exception{

        for(int i = 1 ; i < 15; i ++){

            //String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
            String url = "https://ihuoqiu.com/Home/information?data=W83Lysi$__2B$bHQTpVifRa$__2B$HDg$_2C$$_2C$&pageIndex=" + i;
            System.out.println("定时任务");
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
                        article.setType(2);
                        article.setArticleType(2);
                        article.setCover(info.getImgUrl());
                        article.setTitle(info.getTitle());
                        article.setTag(info.getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(info.getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                        Document doc = Jsoup.connect(url2).get();
                        Elements divs = doc.select("div .article");
                        for (Element element : divs) {
                            System.out.println(element.toString());
                            article.setContent(element.toString());
                        }

                        articleMapper.insert(article);

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
