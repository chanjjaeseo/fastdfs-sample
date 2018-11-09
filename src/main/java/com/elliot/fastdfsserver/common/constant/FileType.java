package com.elliot.fastdfsserver.common.constant;

public enum FileType {

    JPG(1, "jpg"),
    JEPG(2, "jepg");


    private int code;

    private String desc;

    FileType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
