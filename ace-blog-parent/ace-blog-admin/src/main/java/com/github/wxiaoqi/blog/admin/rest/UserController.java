package com.github.wxiaoqi.blog.admin.rest;

import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.CommentBiz;
import com.github.wxiaoqi.blog.admin.biz.UserBiz;
import com.github.wxiaoqi.blog.admin.entity.Comment;
import com.github.wxiaoqi.blog.admin.entity.User;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-08 11:51
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController<UserBiz,User> {

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> page(int limit, int offset, String title){
        Example example = new Example(User.class);
        if(StringUtils.isNotBlank(title))
            example.createCriteria().andLike("title", "%" + title + "%");
        int count = baseBiz.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<User>(count,baseBiz.selectByExample(example));
    }

}
