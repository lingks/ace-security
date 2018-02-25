package com.github.wxiaoqi.blog.admin.rest;

import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.biz.ProjectBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.Project;
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
@RequestMapping("project")
public class ProjectController extends BaseController<ProjectBiz,Project> {

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Project> page(int limit, int offset, String title){
        Example example = new Example(Project.class);
        if(StringUtils.isNotBlank(title))
            example.createCriteria().andLike("title", "%" + title + "%");
        int count = baseBiz.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<Project>(count,baseBiz.selectByExample(example));
    }

}
