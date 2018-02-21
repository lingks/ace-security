package com.github.wxiaoqi.blog.admin.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.AdvertBiz;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.biz.CommentBiz;
import com.github.wxiaoqi.blog.admin.entity.Advert;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.Comment;
import com.github.wxiaoqi.security.common.msg.ListRestResponse;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ace on 2017/7/16.
 */
@RestController
@RequestMapping("api/comment")
public class CommentRest extends BaseController<CommentBiz,Comment> {
    @Autowired
    private CommentBiz articleBiz;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        return new JSONPObject(callback,new ObjectRestResponse<Comment>().rel(true).result(articleBiz.selectById(id)));
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, String callback){
        Page<Comment> objects = PageHelper.startPage(pageIndex, pageSize);
        articleBiz.selectListAll();
        return new JSONPObject(callback, new ListRestResponse<Comment>().rel(true).count(objects.getTotal()).result(objects.getResult()));
    }


}
