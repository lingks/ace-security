package com.github.wxiaoqi.security.gate.controller;

import com.github.wxiaoqi.security.gate.utils.Base64Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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



    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file){

        generateFile(file);

        return "{\"code\":\"0\",\"title\":\"success\",\"src\":\"http://localhost\"}";
    }

    public  String generateFile(MultipartFile file) {

        try {
            // Base64解码
            byte[] fileBytes = file.getBytes();
            OutputStream out = new FileOutputStream(new File("D:/UPLOAD/" + new Date().getTime() + "." + Base64Util.getType(file.getContentType())));




            // 生成地址
            out.write(fileBytes);
            out.flush();
            out.close();
            return "D:/UPLOAD//abc.jpg";
        } catch (Exception e) {
            return null;
        }
    }
}
