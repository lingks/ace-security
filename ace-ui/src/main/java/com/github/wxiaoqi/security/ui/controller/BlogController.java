package com.github.wxiaoqi.security.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ace on 2017/7/15.
 */
@Controller
@RequestMapping("blog")
public class BlogController {
    @RequestMapping("article")
    public String article(){
        return "blog/article/list";
    }
    @RequestMapping("article/edit")
    public String articleEdit(){
        return "blog/article/edit";
    }

    @RequestMapping("flash")
    public String flash(){
        return "blog/flash/list";
    }
    @RequestMapping("flash/edit")
    public String flashEdit(){
        return "blog/flash/edit";
    }

    @RequestMapping("project")
    public String project(){
        return "blog/project/list";
    }
    @RequestMapping("project/edit")
    public String projectEdit(){
        return "blog/project/edit";
    }

    @RequestMapping("partner")
    public String partner(){
        return "blog/partner/list";
    }
    @RequestMapping("partner/edit")
    public String partnerEdit(){
        return "blog/partner/edit";
    }
}
