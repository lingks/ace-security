package com.github.wxiaoqi.blog.admin.rest;

import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.AboutBiz;
import com.github.wxiaoqi.blog.admin.biz.AdvertBiz;
import com.github.wxiaoqi.blog.admin.entity.About;
import com.github.wxiaoqi.blog.admin.entity.Advert;
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
@RequestMapping("about")
public class AboutController extends BaseController<AboutBiz,About> {

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<About> page(int limit, int offset, String name){
        Example example = new Example(About.class);
        if(StringUtils.isNotBlank(name))
            example.createCriteria().andLike("name", "%" + name + "%");
        int count = baseBiz.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<About>(count,baseBiz.selectByExample(example));
    }

}
