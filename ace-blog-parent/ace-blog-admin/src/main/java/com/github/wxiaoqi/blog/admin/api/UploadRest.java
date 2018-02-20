package com.github.wxiaoqi.blog.admin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxiaoqi.blog.admin.util.Base64Util;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by linsheng on 18/2/20.
 */
@RestController
@RequestMapping("api/upload")
public class UploadRest {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestBody String json){

        JSONObject jsonObject = JSON.parseObject(json);
        GenerateImage(jsonObject.getString("fileName"),jsonObject.getString("fileStr"));

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


    // base64字符串转化成图片
    public boolean GenerateImage(String fileName, String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] buffer = imgStr.getBytes();
            // 生成jpeg图片
            String imgFilePath = "/usr/local/upload/" + fileName;// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(buffer);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
