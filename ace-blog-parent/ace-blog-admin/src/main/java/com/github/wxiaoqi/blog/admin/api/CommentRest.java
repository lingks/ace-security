package com.github.wxiaoqi.blog.admin.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.AdvertBiz;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.biz.CommentBiz;
import com.github.wxiaoqi.blog.admin.biz.UserBiz;
import com.github.wxiaoqi.blog.admin.entity.Advert;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.Comment;
import com.github.wxiaoqi.blog.admin.entity.User;
import com.github.wxiaoqi.blog.admin.mapper.CommentMapper;
import com.github.wxiaoqi.security.common.msg.ListRestResponse;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by ace on 2017/7/16.
 */
@RestController
@RequestMapping("api/comment")
public class CommentRest  {

    @Autowired
    private CommentBiz commentBiz;

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private ArticleBiz articleBiz;

    @Autowired
    private CommentMapper commentMapper;
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Comment> add(@RequestBody Comment entity){

        if(entity.getPid()!= null) {
            Article article = articleBiz.selectById(entity.getPid());
            article.setCommentCount(article.getCommentCount() == null ? 0 : article.getCommentCount() + 1);
            article.setHotValue(article.getHotValue() + 1);
            articleBiz.updateSelectiveById(article);
        }
        entity.setCrtTime(new Date());
        commentMapper.insertSelective(entity);
        return new ObjectRestResponse<Comment>().rel(true);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        return new JSONPObject(callback,new ObjectRestResponse<Comment>().rel(true).result(commentBiz.selectById(id)));
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, Integer userId, String callback){


        Example example = new Example(Comment.class);
        if(userId != null){
            example.createCriteria().andEqualTo("id", userId);
        }
        example.setOrderByClause("crt_time desc");

        int count = articleBiz.selectCountByExample(example);

        List<Comment> commentList = commentBiz.selectByExample(example);


        for(Comment comment : commentList){
            if(comment.getCrtUser() != null){
               User u = userBiz.selectById(Integer.parseInt(comment.getCrtUser()));
               if(u != null){
                   comment.setCrtUser(u.getId() + "");
                   comment.setCrtName(u.getUsername());
                   comment.setLogo(u.getLogo());
               }
            }
        }
        return new JSONPObject(callback, new ListRestResponse<Comment>().rel(true).count(count).result(commentList));
    }


}
