package com.elliot.fastdfssample.controller;

import com.elliot.fastdfssample.bean.OperationResult;
import com.elliot.fastdfssample.service.FileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileClient fileClient;

    @PostMapping("/upload")
    @ResponseBody
    public OperationResult uploadFile(MultipartFile file) {
        return fileClient.uploadFile(file);
    }

    @RequestMapping("/download")
    public void downloadFile(String path, HttpServletResponse response) {
        fileClient.downloadFile(path, response);
    }

    @PostMapping("/delete")
    @ResponseBody
    public OperationResult deleteFile(String path) {
        return fileClient.deleteFile(path);
    }

}
