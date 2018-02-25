//package com.github.wxiaoqi.blog.admin.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
///**
// * Created by lings on 2017/10/26.
// */
//@Configuration
//@EnableWebMvc
//public class WebConfig  extends WebMvcConfigurerAdapter {
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        System.out.println("=============================Access-Control-Allow-Origin=================================");
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods(
//                        "GET",
//                        "POST",
//                        "HEAD",
//                        "OPTIONS",
//                        "PUT",
//                        "DELETE")
//                .allowedHeaders(
//                        "Content-Type",
//                        "X-Requested-With",
//                        "accept",
//                        "Origin",
//                        "Access-Control-Request-Method",
//                        "Access-Control-Request-Headers",
//                        "Authorization").allowCredentials(true);
//        System.out.println("============================Access-Control-Allow-Origin==================================");
//    }
//}
