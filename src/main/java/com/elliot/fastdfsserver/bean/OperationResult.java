package com.elliot.fastdfsserver.bean;

public class OperationResult {

    private File file;

    private boolean success;

    private String msg;

    public OperationResult(File file, boolean success, String msg) {
        this.file = file;
        this.success = success;
        this.msg = msg;
    }

    public static OperationResult successOperation(File file, String msg) {
        return new OperationResult(file, true, msg);
    }

    public static OperationResult failOperation(String msg) {
        return new OperationResult(null , false, msg);
    }

}
