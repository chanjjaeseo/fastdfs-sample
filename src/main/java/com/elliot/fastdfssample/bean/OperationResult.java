package com.elliot.fastdfssample.bean;

public class OperationResult {

    private FileInfo fileInfo;

    private boolean success;

    private String msg;

    public OperationResult(FileInfo fileInfo, boolean success, String msg) {
        this.fileInfo = fileInfo;
        this.success = success;
        this.msg = msg;
    }

    public static OperationResult successOperation(FileInfo fileInfo, String msg) {
        return new OperationResult(fileInfo, true, msg);
    }

    public static OperationResult failOperation(String msg) {
        return new OperationResult(null , false, msg);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }
}
