package com.github.wxiaoqi.blog.admin.rest;

import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.mapper.ArticleMapper;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.common.util.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-08 11:51
 */
@Controller
@RequestMapping("article")
public class ArticleController extends BaseController<ArticleBiz,Article> {
    @Autowired
    private ArticleMapper mapper;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Article> add(Article entity){

        System.out.println(entity);
        Integer integer = mapper.selectMaxSort();
        entity.setSort(integer);
        entity.setArticleType(1);
        entity.setStatus(1);
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insertSelective(entity);
        System.out.println(entity);
        return new ObjectRestResponse<Article>().rel(true);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Article> page(int limit, int offset, String title,Integer type){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title))
            example.createCriteria().andLike("title", "%" + title + "%");
        if(type != null){
            example.createCriteria().andEqualTo("type", type);
        }
        example.createCriteria().andBetween("status", 1, 3);
        example.setOrderByClause("crt_time desc");
        int count = baseBiz.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        List<Article> list = baseBiz.selectByExample(example);
        return new TableResultResponse<Article>(count,list);
    }

}
