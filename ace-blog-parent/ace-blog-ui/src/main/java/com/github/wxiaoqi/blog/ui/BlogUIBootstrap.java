package com.github.wxiaoqi.blog.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ace on 2017/7/15.
 */
@SpringBootApplication
public class BlogUIBootstrap {
    public static void main(String[] args) {
      SpringApplication.run(BlogUIBootstrap.class, args);
    }


    @Configuration
    public class DefaultView extends WebMvcConfigurerAdapter{

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("forward:/home");
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
            super.addViewControllers(registry);
        }
    }
}
