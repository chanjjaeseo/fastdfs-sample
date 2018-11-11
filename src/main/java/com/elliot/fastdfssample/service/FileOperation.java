package com.elliot.fastdfssample.service;

import com.elliot.fastdfssample.bean.OperationResult;
import org.csource.common.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileOperation {

    OperationResult uploadFile(MultipartFile file);

    void downloadFile(String path, HttpServletResponse response);

    OperationResult deleteFile(String path);

}
