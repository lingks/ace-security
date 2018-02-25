package com.github.wxiaoqi.blog.admin.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.mapper.AdvertMapper;
import com.github.wxiaoqi.blog.admin.mapper.ArticleMapper;
import com.github.wxiaoqi.security.common.msg.ListRestResponse;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

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
        return new JSONPObject(callback,new ObjectRestResponse<Article>().rel(true).result(articleBiz.selectById(id)));
    }
//    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public JSONPObject get(int pageIndex, int pageSize, String callback,int type){
//        Page<Article> objects = PageHelper.startPage(pageIndex, pageSize);
//        articleBiz.selectListAll();
//        return new JSONPObject(callback, new ListRestResponse<Article>().rel(true).count(objects.getTotal()).result(objects.getResult()));
//    }

    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize,String authorId,String title,Integer type,String orderField,String orderBy,String callback){
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
        PageHelper.startPage(pageIndex, pageSize);
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
        System.out.println(entity);

        mapper.insertSelective(entity);
        System.out.println(entity);
        return new ObjectRestResponse<Article>().rel(true);
    }
}

