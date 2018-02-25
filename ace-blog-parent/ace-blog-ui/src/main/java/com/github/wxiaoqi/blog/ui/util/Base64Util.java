package com.github.wxiaoqi.blog.ui.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;

public class Base64Util {
    protected static Logger logger = LoggerFactory.getLogger(Base64Util.class);

    @SuppressWarnings("restriction")
    /**
     * base64解密
     *
     * @param baseStr
     *            Base64位字符串
     * @param temp
     *            生成地址
     * @return
     */
    public static String generateFile(String baseStr, String username) {
        if (baseStr == null) // 数据为空
            return null;
        String filetype = "";
        // 判断文件类型
        if (loadFilePath(username).equals("") || null == loadFilePath(username))
            return null;
        FileTypeUtil[] fileTypes = FileTypeUtil.values();
        for (FileTypeUtil type : fileTypes) {
            if (baseStr.indexOf(type.getValue()) > 0) {
                filetype = type.getIndex();
                baseStr = baseStr.replaceAll("data:" + type.getValue() + ";base64,", "");
                break;
            }
        }
        String filePath = loadFilePath(username) + filetype;
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(baseStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成地址
            OutputStream out = new FileOutputStream(filePath);
            out.write(b);
            out.flush();
            out.close();
            return filePath;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断文件类型
     *
     * @param
     *
     * @return 文件类型
     */
    public static FileTypeUtil getType(String baseStr) throws IOException {
        if (baseStr == null || baseStr.length() == 0) {
            return null;
        }

        FileTypeUtil[] fileTypes = FileTypeUtil.values();

        for (FileTypeUtil type : fileTypes) {
            if (baseStr.equals(type.getValue())) {
                return type;
            }
        }
        return null;
    }

    /**
     * 上传文件地址
     */
    public static String loadFilePath(String username) {
        String filePath = "upload.properties";
        LoadPopertiesFile.loadSqlFile(filePath);
        StringBuffer br = new StringBuffer();
        br.append(Constants.loadSqlMap.get("uploadPath".toUpperCase()) + File.separator);
        if (username != null && !"".equals(username)) {
            br.append(username + File.separator);
        }
        logger.debug("文件目录" + br.toString());
        if (createDir(br.toString())) {
            try {
                br.append(new String((DateHandler.dateToStr2(new Date(), "yyyyMMddhhmmssSSS")).getBytes("gb2312"),
                        "ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return br.toString();
        }
        return null;
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
       
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目录
        if(!dir.exists()){
            if (dir.mkdirs()) {
                logger.debug("创建目录" + destDirName + "成功！");
                return true;
            } else {
                logger.debug("创建目录" + destDirName + "失败！");
                return false;
            }
        }else{
            return true;
        }
    }

    @SuppressWarnings("restriction")
    public static String uploadFile(String baseStr) {
        if (baseStr == null) // 数据为空
            return null;
        String filetype = "";
        // 判断文件类型
        if (getLoadFilePath().equals("") || null == getLoadFilePath())
            return null;
        FileTypeUtil[] fileTypes = FileTypeUtil.values();
        for (FileTypeUtil type : fileTypes) {
            if (baseStr.indexOf(type.getValue()) > 0) {
                filetype = type.getIndex();
                baseStr = baseStr.replaceAll("data:" + type.getValue() + ";base64,", "");
                break;
            }
        }
        String filePath = getLoadFilePath();
        String fileName = getFileName() + filetype;
        filePath = filePath + fileName;
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(baseStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成地址
            OutputStream out = new FileOutputStream(filePath);
            out.write(b);
            out.flush();
            out.close();
            return fileName;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 得到上传文件地址
     */
    public static String getLoadFilePath() {
        String filePath = "upload.properties";
        LoadPopertiesFile.loadSqlFile(filePath);
        StringBuffer br = new StringBuffer();
        br.append(Constants.loadSqlMap.get("uploadPath".toUpperCase()) + File.separator);
        if (createDir(br.toString())) {
            return br.toString();
        }
        return null;
    }

    /**
     * 上传成功后重新生成的文件名
     *
     * @return
     */
    public static String getFileName() {
        StringBuffer br = new StringBuffer();
        try {
            br.append(
                    new String((DateHandler.dateToStr2(new Date(), "yyyyMMddhhmmssSSS")).getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return br.toString();
    }

    @SuppressWarnings("restriction")
    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    /**
     * 文件生成base位
     *
     * @param in
     * @return
     * @throws IOException
     */
    @SuppressWarnings("restriction")
    public static String ioToBase64(InputStream in) {
        String strBase64 = null;
        try {
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = new sun.misc.BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }

    public static String getFileSize(File file) {
        Long fileSize = file.length();
        if (fileSize.equals((Long) 0L)) {
            return "0KB";
        }
        double kiloByte = fileSize / 1024L;
        if (kiloByte <= 1) {
            return "1KB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte + 1));
            return result1.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
        return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
    }

}
