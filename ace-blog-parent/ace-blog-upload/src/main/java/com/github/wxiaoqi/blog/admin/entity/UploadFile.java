package com.github.wxiaoqi.blog.admin.entity;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/3.
 */
public class UploadFile implements Serializable {
    private File file;
    private String filename;
    private String contentType;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "file=" + file +
                ", filename='" + filename + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
