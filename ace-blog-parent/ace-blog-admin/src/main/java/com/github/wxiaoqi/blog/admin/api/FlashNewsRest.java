package com.github.wxiaoqi.blog.admin.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.biz.FlashNewsBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.FlashNews;
import com.github.wxiaoqi.security.common.msg.ListRestResponse;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ace on 2017/7/16.
 */
@RestController
@RequestMapping("api/flash")
public class FlashNewsRest {
    @Autowired
    private FlashNewsBiz flashNewsBiz;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        return new JSONPObject(callback,new ObjectRestResponse<Article>().rel(true).result(flashNewsBiz.selectById(id)));
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, String callback){
        Page<FlashNews> objects = PageHelper.startPage(pageIndex, pageSize);
        flashNewsBiz.selectListAll();
        return new JSONPObject(callback, new ListRestResponse<FlashNews>().rel(true).count(objects.getTotal()).result(objects.getResult()));
    }
}
