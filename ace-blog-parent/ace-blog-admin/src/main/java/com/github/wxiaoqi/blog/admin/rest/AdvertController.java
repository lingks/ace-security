package com.github.wxiaoqi.blog.admin.rest;

import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.AdvertBiz;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.entity.Advert;
import com.github.wxiaoqi.blog.admin.entity.Article;
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
@RequestMapping("advert")
public class AdvertController extends BaseController<AdvertBiz,Advert> {

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Advert> page(int limit, int offset, String title){
        Example example = new Example(Advert.class);
        if(StringUtils.isNotBlank(title))
            example.createCriteria().andLike("title", "%" + title + "%");
        int count = baseBiz.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<Advert>(count,baseBiz.selectByExample(example));
    }

}
