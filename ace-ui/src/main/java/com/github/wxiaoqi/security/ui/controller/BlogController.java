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
    @RequestMapping("advert")
    public String advert(){
        return "blog/advert/list";
    }
    @RequestMapping("advert/edit")
    public String advertEdit(){
        return "blog/advert/edit";
    }

    @RequestMapping("ship")
    public String ship(){
        return "blog/ship/list";
    }
    @RequestMapping("ship/edit")
    public String shipEdit(){
        return "blog/ship/edit";
    }

    @RequestMapping("author")
    public String author(){
        return "blog/author/list";
    }
    @RequestMapping("author/edit")
    public String authorEdit(){
        return "blog/author/edit";
    }
    @RequestMapping("comment")
    public String comment(){
        return "blog/comment/list";
    }
    @RequestMapping("comment/edit")
    public String commentEdit(){
        return "blog/message/edit";
    }
    @RequestMapping("message")
    public String message(){
        return "blog/message/list";
    }
    @RequestMapping("message/edit")
    public String messageEdit(){
        return "blog/message/edit";
    }
    @RequestMapping("about")
    public String about(){
        return "blog/about/list";
    }
    @RequestMapping("about/edit")
    public String aboutEdit(){
        return "blog/about/edit";
    }
}
