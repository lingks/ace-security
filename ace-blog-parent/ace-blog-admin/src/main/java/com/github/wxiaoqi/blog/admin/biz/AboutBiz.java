package com.github.wxiaoqi.blog.admin.biz;

import com.github.wxiaoqi.blog.admin.entity.About;
import com.github.wxiaoqi.blog.admin.entity.Advert;
import com.github.wxiaoqi.blog.admin.mapper.AboutMapper;
import com.github.wxiaoqi.blog.admin.mapper.AdvertMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-12 8:48
 */
@Service
public class AboutBiz extends BaseBiz<AboutMapper,About> {
}
