package com.github.wxiaoqi.blog.admin.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.FriendshipBiz;
import com.github.wxiaoqi.blog.admin.biz.ProjectBiz;
import com.github.wxiaoqi.blog.admin.entity.Friendship;
import com.github.wxiaoqi.blog.admin.entity.Project;
import com.github.wxiaoqi.blog.admin.entity.StrategicPartner;
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
@RequestMapping("api/ship")
public class FriendshipRest {
    @Autowired
    private FriendshipBiz articleBiz;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        return new JSONPObject(callback,new ObjectRestResponse<Friendship>().rel(true).result(articleBiz.selectById(id)));
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, String callback){
        Page<Friendship> objects = PageHelper.startPage(pageIndex, pageSize);
        articleBiz.selectListAll();
        return new JSONPObject(callback, new ListRestResponse<Friendship>().rel(true).count(objects.getTotal()).result(objects.getResult()));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(String callback){


        return new JSONPObject(callback,
                new ListRestResponse<Friendship>().rel(true).result(articleBiz.selectListAll()));
    }
}
