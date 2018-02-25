package com.github.wxiaoqi.blog.ui.util;


public enum FileTypeUtil {

    /**
     * JEPG.
     */
    JPEG(".jpeg","image/jpeg"),

    /**
     * PNG.
     */
    PNG(".png","image/png"),

    /**
     * GIF.
     */
    GIF(".gif","image/gif"),

    /**
     * ZIP Archive.
     */
    ZIP(".zip","application/x-zip-compressed"),

    /**
     * DOC.
     */
    DOC(".dox","application/msword"),

    /**
     * DOCX.
     */
    DOCX(".docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document"),

    /**
     * XLS.
     */
    XLS(".xls","application/vnd.ms-excel"),

    /**
     * xls2 (mpg).
     */
    XLS2(".xls","application/x-excel"),

    /**
     * xml (mpg).
     */
    XML(".xml","text/xml"),

    /**
     * XLSX.
     */
    XLSX(".xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    private String index="";

    private String value = "";

    /**
     * Constructor.
     *
     * @param type
     */
    private FileTypeUtil(String index,String value) {
        this.value = value;
        this.index=index;
    }



    public String getIndex() {
        return index;
    }



    public void setIndex(String index) {
        this.index = index;
    }



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
