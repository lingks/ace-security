package com.github.wxiaoqi.security.gate.controller;

import com.github.wxiaoqi.security.gate.utils.Base64Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
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
    public Response upload(MultipartFile file, HttpServletResponse response){



        return new Response(0,"success",generateFile(file));
    }



    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(MultipartFile file){

        return "{\"code\":0,\"msg\":\"success\",\"data\":{\"src\":\""+ generateFile(file) +"\"}}";
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
            return uploadPath + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

class Response{
    Integer code;
    String title;
    String src;

    public Response() {
    }

    public Response(Integer code, String title, String src) {

        this.code = code;
        this.title = title;
        this.src = src;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}