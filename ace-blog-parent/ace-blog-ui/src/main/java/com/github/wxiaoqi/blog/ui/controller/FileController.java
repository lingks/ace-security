package com.github.wxiaoqi.blog.ui.controller;

import com.github.wxiaoqi.blog.ui.util.Base64Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by linsheng on 18/2/24.
 */

@Controller
@RequestMapping("file")
public class FileController {


    @Value("${upload.path}")
    private String uploadPath;
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file){


        System.out.println(uploadPath);
        return "{\"code\":\"0\",\"title\":\"success\",\"src\":\""+ generateFile(file) +"\"}";
    }


    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(MultipartFile file){


        return "{\"code\":0,\"msg\":\"success\",\"data\":{\"src\":\"http://localhost:9700/images/logo.svg\"}}";
        //return "{\"code\":0,\"msg\":\"success\",\"data\":{\"src\":\""+ generateFile(file) +"\"}}";
    }

    public  String generateFile(MultipartFile file) {

        try {
            // Base64解码
            byte[] b = file.getBytes();

            System.out.println(b.length);

            System.out.println("写入文件");
            String fileName = new Date().getTime() + "." + Base64Util.getType(file.getContentType());
            String filePath = "D://upload/" + fileName;
            // 生成地址
            OutputStream out = new FileOutputStream(new File(filePath));
            out.write(b);
            out.flush();
            out.close();
            return uploadPath+fileName;
        } catch (Exception e) {
            return null;
        }
    }
}
