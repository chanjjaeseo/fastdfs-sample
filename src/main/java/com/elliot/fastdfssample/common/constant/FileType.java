package com.elliot.fastdfssample.common.constant;

public enum FileType {

    JPG(1, "jpg"),
    JEPG(2, "jpeg"),
    PNG(3, "png"),
    GIF(4, "gif"),
    PDF(5, "pdf"),
    PPT(6, "ppt"),
    PPTX(7, "pptx"),
    XLS(8, "xls"),
    XLSX(9, "xlsx"),
    DOC(10, "doc"),
    DOCX(11, "docx"),
    MP3(12, "mp3"),
    MP4(13, "mp4"),
    FLV(14, "flv"),
    ZIP(15, "zip"),
    RAR(16, "rar");

    private int code;

    private String desc;

    FileType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static int getFileType(String ext){
        final int ERROR_CODE = -1;
        for(FileType fileType : values()) {
            if(fileType.getDesc().equals(ext)) {
                return fileType.getCode();
            }
        }
        return ERROR_CODE;
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
