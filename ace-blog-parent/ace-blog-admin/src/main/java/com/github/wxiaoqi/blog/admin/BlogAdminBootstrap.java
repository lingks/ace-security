package com.github.wxiaoqi.blog.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-05-25 12:44
 */
@EnableEurekaClient
@EnableHystrix
@EnableScheduling
@ComponentScan
@SpringBootApplication
public class BlogAdminBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BlogAdminBootstrap.class).web(true).run(args);    }
}
