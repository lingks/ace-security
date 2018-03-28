package com.github.wxiaoqi.blog.admin.biz;

import com.github.wxiaoqi.blog.admin.api.vo.ArticleInfo;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.mapper.ArticleMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-12 8:48
 */
@Service
public class ArticleBiz extends BaseBiz<ArticleMapper,Article> {

    @Autowired
    private ArticleMapper mapper;
    public ArticleInfo selectByTitle(@Param("title") String title){

        return mapper.selectByTitle(title);
    }
}
