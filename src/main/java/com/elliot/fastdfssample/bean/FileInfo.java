package com.elliot.fastdfssample.bean;

import com.elliot.fastdfssample.common.constant.FileType;

import java.util.Map;

public class FileInfo {

    private String originalName;

    private int type;

    private String path;

    private String token;

    private Map<String, String> description;

    private long size;

    public FileInfo() {

    }

    public FileInfo(String originalName, FileType type, String path) {
        this(originalName, type.getCode(), path);
    }

    public FileInfo(String originalName, int type, String path) {
        this(originalName, type, path, null, null);
    }

    public FileInfo(String originalName, int type, String path, String token, Map<String, String> description) {
        this.originalName = originalName;
        this.type = type;
        this.path = path;
        this.token = token;
        this.description = description;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return path;
    }

    public void setUrl(String url) {
        this.path = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }
}
