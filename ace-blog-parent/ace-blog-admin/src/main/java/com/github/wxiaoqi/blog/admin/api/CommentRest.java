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
import org.springframework.web.bind.annotation.*;

/**
 * Created by ace on 2017/7/16.
 */
@RestController
@RequestMapping("api/comment")
public class CommentRest  {

    @Autowired
    private CommentBiz commentBiz;

    @Autowired
    private ArticleBiz articleBiz;
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Comment> add(Comment entity){

        Article article = articleBiz.selectById(entity.getPid());
        article.setCommentCount(article.getCommentCount() + 1);
        articleBiz.updateSelectiveById(article);
        commentBiz.insertSelective(entity);
        return new ObjectRestResponse<Comment>().rel(true);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        return new JSONPObject(callback,new ObjectRestResponse<Comment>().rel(true).result(commentBiz.selectById(id)));
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, String callback){
        Page<Comment> objects = PageHelper.startPage(pageIndex, pageSize);
        commentBiz.selectListAll();
        return new JSONPObject(callback, new ListRestResponse<Comment>().rel(true).count(objects.getTotal()).result(objects.getResult()));
    }


}
