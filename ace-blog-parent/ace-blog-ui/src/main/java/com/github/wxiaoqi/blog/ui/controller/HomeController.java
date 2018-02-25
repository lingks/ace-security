package com.github.wxiaoqi.blog.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ace on 2017/7/15.
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    @RequestMapping("/article")
    public String article(){
        return "article";
    }
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable String id, Map<String,Object> map){
        map.put("articleId",id);
        return "detail";
    }
    @RequestMapping("/resource")
    public String resource(){
        return "resource";
    }
    @RequestMapping("/timeline")
    public String timeline(){
        return "timeline";
    }

    @RequestMapping("/flash")
    public String flash(){
        return "flash";
    }

    @RequestMapping("/project")
    public String project(){
        return "project";
    }

    @RequestMapping("/author")
    public String author(){
        return "author";
    }
    @RequestMapping("/udetail/{authorId}")
    public String udetail(@PathVariable Integer authorId, Model model,HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("authorId",authorId);
        request.setAttribute("authorId",authorId);
        return "udetail";
    }
    @RequestMapping("/center")
    public String center(){
        return "center";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/writting")
    public String writting(){
        return "writting";
    }
}
