package com.elliot.fastdfsserver.bean;

import com.elliot.fastdfsserver.common.constant.FileType;

public class File {

    private String name;

    private int type;

    private String url;

    private String token;

    private String description;

    public File(String name, FileType type, String url) {
        this(name, type.getCode(), url);
    }

    public File(String name, int type, String url) {
        this(name, type, url, null, null);
    }

    public File(String name, int type, String url, String token, String description) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.token = token;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
