package com.github.wxiaoqi.blog.admin.mapper;

import com.github.wxiaoqi.blog.admin.api.vo.ArticleInfo;
import com.github.wxiaoqi.blog.admin.entity.Article;
import feign.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleMapper extends Mapper<Article> {

    Integer selectMaxSort();

    Integer selectMaxTop();

    ArticleInfo selectByTitle(@Param("title") String title);
}