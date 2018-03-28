package com.github.wxiaoqi.blog.admin.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.api.vo.ArticleData;
import com.github.wxiaoqi.blog.admin.api.vo.ArticleInfo;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.Comment;
import com.github.wxiaoqi.blog.admin.mapper.AdvertMapper;
import com.github.wxiaoqi.blog.admin.mapper.ArticleMapper;
import com.github.wxiaoqi.blog.admin.util.HttpClientUtil;
import com.github.wxiaoqi.security.common.msg.ListRestResponse;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by ace on 2017/7/16.
 */
@RestController
@RequestMapping("api/article")
public class ArticleRest {

    @Autowired
    private ArticleMapper mapper;
    @Autowired
    private ArticleBiz articleBiz;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){

        Article article = articleBiz.selectById(id);
        if(article != null){
            article.setHotValue(article.getHotValue() + 0.3);
            articleBiz.updateSelectiveById(article);
        }
        return new JSONPObject(callback,new ObjectRestResponse<Article>().rel(true).result(articleBiz.selectById(id)));
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Article> del(@PathVariable Integer id){

        return new ObjectRestResponse<Article>().rel(true);
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Article> update(@PathVariable Integer id){
        Article entity = articleBiz.selectById(id);
        entity.setPageView(entity.getPageView() + 1);
        articleBiz.updateSelectiveById(entity);
        return new ObjectRestResponse<Article>().rel(true);
    }


    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Article> edit(Article article){
        Article entity = articleBiz.selectById(article.getId());
        article.setUpdTime(new Date());

        if(entity != null)articleBiz.updateSelectiveById(article);
        return new ObjectRestResponse<Article>().rel(true);
    }


    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(@RequestParam(name = "pageIndex") Integer pageIndex,
                           @RequestParam(name = "pageSize") Integer pageSize,String authorId,String title,
                           Integer type,String orderField,String orderBy,
                           @RequestParam(name = "scroller" ,defaultValue = "0") Integer scroller, String callback){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }
        if(type != null && type > 0){
            example.createCriteria().andEqualTo("type", type);
        }

        if(scroller > 0){
            example.createCriteria().andEqualTo("scroller", scroller);
        }
        if(StringUtils.isNotBlank(authorId)){

            example.createCriteria().andEqualTo("crtUser", authorId);
        }else{
            example.createCriteria().andEqualTo("status", 2);
        }

        if(StringUtils.isNotBlank(orderField)){
            example.setOrderByClause("hot_value desc");
        }

        example.setOrderByClause("crt_time desc");
        int count = articleBiz.selectCountByExample(example);

        PageHelper.startPage(pageIndex, pageSize);
        List<Article> list = articleBiz.selectByExample(example);
        return new JSONPObject(callback, new ListRestResponse<Article>().rel(true).count(count).result(list));

    }


    @RequestMapping(value = "/find",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject find(int page, int limit,String authorId,String title,Integer type,String orderField,String orderBy,String callback){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }
        if(type != null){
            example.createCriteria().andEqualTo("type", type);
        }

        if(StringUtils.isNotBlank(authorId)){
            example.createCriteria().andEqualTo("crtUser", authorId);
        }

        if(StringUtils.isNotBlank(orderField)){
            example.setOrderByClause(orderField + " " + orderBy==null?"asc":"desc");
        }

        int count = articleBiz.selectCountByExample(example);
        PageHelper.startPage(page, limit);
        return new JSONPObject(callback, new ListRestResponse<Article>().rel(true).count(count).result(articleBiz.selectByExample(example)));

    }

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject findList(int page, int limit,String authorId,String title,Integer type,String orderField,String orderBy,String callback){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }
        if(type != null){
            example.createCriteria().andEqualTo("type", type);
        }

        if(StringUtils.isNotBlank(authorId)){
            example.createCriteria().andEqualTo("crtUser", authorId);
        }

        if(StringUtils.isNotBlank(orderField)){
            example.setOrderByClause(orderField + " " + orderBy==null?"asc":"desc");
        }

        int count = articleBiz.selectCountByExample(example);
        PageHelper.startPage(page, limit);
        return new JSONPObject(callback, new ListRestResponse<Article>().rel(true).count(count).result(articleBiz.selectByExample(example)));

    }


    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Article> add(Article entity){
       // System.out.println(entity);
        Integer integer = mapper.selectMaxSort();
        entity.setSort(integer);
        entity.setArticleType(0);
        mapper.insertSelective(entity);
        System.out.println(entity);
        return new ObjectRestResponse<Article>().rel(true);
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Article> auth(@RequestParam Integer id,@RequestParam Integer userId,Integer status){
         Article article = articleBiz.selectById(id);
         if(article != null){
             article.setStatus(1);
             articleBiz.updateSelectiveById(article);
         }
        return new ObjectRestResponse<Article>().rel(true);
    }

    @RequestMapping(value = "/top/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Article> top(Article entity){
        entity.setIsTop(mapper.selectMaxTop());
        mapper.updateByPrimaryKey(entity);
        return new ObjectRestResponse<Article>().rel(true);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    @ResponseBody
    public String insert() throws IOException{
        for(int i = 1 ; i < 10000; i ++){

            //String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
            String url = "https://ihuoqiu.com/Home/information?data=W83Lysi$__2B$bHQTpVifRa$__2B$HDg$_2C$$_2C$&pageIndex=" + i;

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
                            Elements divs = doc.select("div .hq_information_info");
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

                            mapper.insert(article);
                        }
                    }else{
                        ArticleInfo info = articleBiz.selectByTitle(data.getArticleInfo().getTitle());
                        Article article = new Article();
                        article.setId(Integer.parseInt(info.getID()));
                        article.setCrtTime(new Date());
                        article.setUpdTime(new Date());
                        article.setStatus(2);
                        article.setArticleType(1);
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTitle(data.getArticleInfo().getTitle());
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTag(data.getArticleInfo().getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(data.getArticleInfo().getShortDescription());
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
                                divs = doc.select("div .hq_information_content");
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());

                                }
                            }
                             mapper.updateByPrimaryKey(article);
                        }
                    }
                }
            }
        }



        for(int i = 1 ; i < 2000; i ++) {

            //String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=" + i;
            String url = "https://ihuoqiu.com/Home/information?data=BgZLXDwZ8H336uMaxH6fIA__2C__2C&pageIndex=" + i;
            String s = HttpClientUtil.doPost(url);
            JSONObject object = JSONObject.parseObject(s);
            if (object.getString("code").equals("200") && object.getBoolean("success") == true) {
                String msg = object.getString("msg");
                List<ArticleData> list = JSONArray.parseArray(msg, ArticleData.class);

                for (ArticleData data : list) {

                    if (check(data.getArticleInfo().getTitle())) {
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
                        if (data.getData1() != null && !"".equals(data.getData1())) {
                            String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                            Document doc = Jsoup.connect(url2).get();
                            Elements divs = doc.select("div .hq_information_info");
                            if (divs.size() > 0) {
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                    System.out.println(element.toString());
                                }
                            } else {
                                divs = doc.select("div .hq_information_content");
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                    System.out.println(element.toString());

                                }
                            }

                            mapper.insert(article);
                        }
                    } else {
                        ArticleInfo info = mapper.selectByTitle(data.getArticleInfo().getTitle());
                        Article article = new Article();
                        article.setId(Integer.parseInt(info.getID()));
                        article.setCrtTime(new Date());
                        article.setUpdTime(new Date());
                        article.setStatus(2);
                        article.setArticleType(2);
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTitle(data.getArticleInfo().getTitle());
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTag(data.getArticleInfo().getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(data.getArticleInfo().getShortDescription());
                        article.setHotValue(new Random().nextInt(100));
                        article.setSource(data.getArticleInfo().getSouce());
                        if (data.getData1() != null && !"".equals(data.getData1())) {
                            String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
                            Document doc = Jsoup.connect(url2).get();
                            Elements divs = doc.select("div .hq_information_info");
                            if (divs.size() > 0) {
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());
                                }
                            } else {
                                divs = doc.select("div .hq_information_content");
                                for (Element element : divs) {
                                    // System.out.println(element.toString());
                                    article.setContent(element.toString());

                                }
                            }
                            mapper.updateByPrimaryKey(article);
                        }
                    }
                }
            }

        }

        for(int i = 1 ; i < 1000; i ++){

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
                            Elements divs = doc.select("div .hq_information_content");
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

                            mapper.insert(article);
                        }
                    }else{
                        ArticleInfo info = mapper.selectByTitle(data.getArticleInfo().getTitle());
                        Article article = new Article();
                        article.setId(Integer.parseInt(info.getID()));
                        article.setCrtTime(new Date());
                        article.setUpdTime(new Date());
                        article.setStatus(2);
                        article.setArticleType(3);
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTitle(data.getArticleInfo().getTitle());
                        article.setCover(data.getArticleInfo().getImgUrl());
                        article.setTag(data.getArticleInfo().getTag());
                        article.setPageView(info.getViewCount());
                        article.setRemark(data.getArticleInfo().getShortDescription());
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
                            mapper.updateByPrimaryKey(article);
                        }
                    }
                }
            }
        }

        return "";
    }
    public boolean check(String title){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }

        int count = mapper.selectCountByExample(example);
        if(count > 0){
            return false;
        }
        return true;
    }

}

