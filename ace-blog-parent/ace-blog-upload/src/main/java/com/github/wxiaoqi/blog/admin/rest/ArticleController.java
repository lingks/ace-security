package com.github.wxiaoqi.blog.admin.rest;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.UploadFile;
import com.github.wxiaoqi.blog.admin.util.Base64Util;
import com.github.wxiaoqi.blog.admin.util.FileTypeUtil;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.rest.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import java.util.Date;
import java.io.*;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-08 11:51
 */
@Controller
@RequestMapping("article")
public class ArticleController extends BaseController<ArticleBiz,Article> {

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Article> page(int limit, int offset, String title){
        Example example = new Example(Article.class);
        if(StringUtils.isNotBlank(title))
            example.createCriteria().andLike("title", "%" + title + "%");
        int count = baseBiz.selectCountByExample(example);
        PageHelper.startPage(offset, limit);
        return new TableResultResponse<Article>(count,baseBiz.selectByExample(example));
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file){

        generateFile(file);

        return "'{\"code\":\"0\",\"title\":\"success\",\"src\":\"http://localhost\"}'";
    }

    public  String generateFile(MultipartFile file) {

        try {
            // Base64解码
            byte[] b = file.getBytes();
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            // 生成地址
            OutputStream out = new FileOutputStream(new File("D:/UPLOAD/" + new Date().getTime() + "." + Base64Util.getType(file.getContentType())));
            out.write(b);
            out.flush();
            out.close();
            return "D:/UPLOAD//abc.jpg";
        } catch (Exception e) {
            return null;
        }
    }
}
