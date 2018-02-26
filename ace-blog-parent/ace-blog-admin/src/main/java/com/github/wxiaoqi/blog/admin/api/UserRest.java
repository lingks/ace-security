package com.github.wxiaoqi.blog.admin.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.CommentBiz;
import com.github.wxiaoqi.blog.admin.biz.UserBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.Comment;
import com.github.wxiaoqi.blog.admin.entity.User;
import com.github.wxiaoqi.security.common.constant.UserConstant;
import com.github.wxiaoqi.security.common.msg.ListRestResponse;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ace on 2017/7/16.
 */
@RestController
@RequestMapping("api/user")
public class UserRest {

    @Autowired
    private UserBiz articleBiz;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<User> add(User entity){

        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        articleBiz.insertSelective(entity);
        return new ObjectRestResponse<User>().rel(true);
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Comment> register(User entity,String callback){



        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", entity.getUsername());
        List<User> users = articleBiz.selectByExample(example);
        if(users != null && users.size() > 0){
            return new ObjectRestResponse<Comment>().rel(false);
        }
        articleBiz.insertSelective(entity);

        example.createCriteria().andEqualTo("password", entity.getPassword());
        users = articleBiz.selectByExample(example);

        return new ObjectRestResponse<Comment>().rel(true).result(users);

    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public JSONPObject login(String username, String password,String callback) {
        Example example = new Example(User.class);
        if (StringUtils.isNotBlank(username)){
            example.createCriteria().andEqualTo("username", username);
        }
        if(StringUtils.isNotBlank(password)){
            example.createCriteria().andEqualTo("password", password);
        }

        return new JSONPObject(callback,new ObjectRestResponse<User>().rel(true).result(articleBiz.selectByExample(example)));

    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        List<User> users = new ArrayList<User>();
        users.add(articleBiz.selectById(id));
        return new JSONPObject(callback,new ListRestResponse<User>().rel(true).result(users));
    }


    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, Integer tuijian, String callback){
        Example example = new Example(User.class);

        if(tuijian != null){
            example.createCriteria().andEqualTo("tuijian", tuijian);
        }



        int count = articleBiz.selectCountByExample(example);
        PageHelper.startPage(pageIndex, pageSize);
        return new JSONPObject(callback, new ListRestResponse<User>().rel(true).count(count).result(articleBiz.selectByExample(example)));
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<User> update(User entity) throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(entity.getTirthyStr() != null && !"".equals(entity.getTirthyStr())){
            entity.setBirthy(df.parse(entity.getTirthyStr()));
        }
        articleBiz.updateSelectiveById(entity);
        return new ObjectRestResponse<User>().rel(true);
    }

}
