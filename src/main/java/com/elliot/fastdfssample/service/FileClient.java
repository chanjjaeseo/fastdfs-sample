package com.elliot.fastdfssample.service;

import com.elliot.fastdfssample.bean.FileInfo;
import com.elliot.fastdfssample.bean.OperationResult;
import com.elliot.fastdfssample.common.constant.FileType;
import com.elliot.fastdfssample.common.util.FileHelper;
import com.elliot.fastdfssample.common.util.MapConverter;
import com.elliot.fastdfssample.config.Gloabls;
import com.elliot.fastdfssample.fdfs.TrackerServerPool;
import com.sun.tools.hat.internal.parser.ReadBuffer;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

@Service
public class FileClient implements FileOperation{

    private static Logger logger = LoggerFactory.getLogger(FileHelper.class);

    private final static long maxSize = 1024 * 1024;

    @Autowired
    private Gloabls gloabls;

    @Autowired
    private TrackerServerPool trackerServerPool;

    public void downloadFile(String path, HttpServletResponse response) {
        TrackerServer trackerServer = TrackerServerPool.borrowTrckerServerFromPool();
        StorageClient1 storageClient = new StorageClient1(trackerServer, null);
        String extensionName = FileHelper.getFileExtension(path);
        response.addHeader("Content-Disposition", "attachment;filename=test." + extensionName);
        response.setContentType("application/octet-stream");
        try {

            byte[] fileBytes = storageClient.download_file1(path);
            TrackerServerPool.returnTrackerServer(trackerServer);
            if (fileBytes != null && fileBytes.length > 0) {
                outputByteFiles(response, fileBytes);
            }
        } catch (Exception e) {
            logger.error("download file error", e);
        }
    }

    private void outputByteFiles(HttpServletResponse response, byte[] fileBytes) throws IOException {
        InputStream is = new ByteArrayInputStream(fileBytes);
        OutputStream os = response.getOutputStream();
        byte[] byteBuffer = new byte[1024 * 128];
        while (is.read(byteBuffer) > 0) {
            os.write(byteBuffer);
        }
        os.flush();
        os.close();
    }

    public OperationResult deleteFile(String path) {
        TrackerServer trackerServer = TrackerServerPool.borrowTrckerServerFromPool();
        StorageClient1 storageClient = new StorageClient1(trackerServer, null);
        try {
            if (storageClient.delete_file1(path) != 0) {
                return OperationResult.failOperation("delete file failure , you could try again");
            }
        } catch (Exception e) {
            logger.error("delete file error", e);
            return OperationResult.failOperation("delete file error");
        }
        return OperationResult.successOperation(null, "");
    }

    public OperationResult uploadFile(MultipartFile file) {
        return this.uploadFile(file, null, null);
    }

    public OperationResult uploadFile(MultipartFile file, String groupName, Map<String, String> fileDesc) {
        String originalName = file.getOriginalFilename();
        if (!FileHelper.isSuport(originalName)) {
            return OperationResult.failOperation("file type is not suport");
        }
        long fileSize = file.getSize();
        byte[] fileBytes;
        try {
            fileBytes  = file.getBytes();
        } catch (IOException e) {
            logger.error("file can`t get input stream");
            return OperationResult.failOperation("error");
        }
        return this.uploadFile(fileBytes, originalName, fileSize, groupName, fileDesc);
    }


    public OperationResult uploadFile(byte[] fileBytes,
                                      String originalName,
                                      long fileSize,
                                      String groupName,
                                      Map<String, String> fileDesc) {
        String fileExt = FileHelper.getFileExtension(originalName);
        TrackerServer trackerServer = TrackerServerPool.borrowTrckerServerFromPool();
        StorageClient1 storageClient = new StorageClient1(trackerServer, null);
        NameValuePair[] nvps = MapConverter.convertToPair(fileDesc);
        String path;
        try {
            if (groupName == null) {
                path = storageClient.upload_file1(fileBytes, fileExt, nvps);
            } else {
                path = storageClient.upload_file1(groupName, fileBytes, fileExt, nvps);
            }

            TrackerServerPool.returnTrackerServer(trackerServer);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalName(originalName);
            fileInfo.setSize(fileSize);
            fileInfo.setDescription(fileDesc);
            fileInfo.setUrl(gloabls.getTrackerHost()+ "/" + path);
            fileInfo.setType(FileType.getFileType(fileExt));
            saveFileInfoToDB(fileInfo);
            logger.info(fileInfo.getUrl());
            return OperationResult.successOperation(fileInfo, "success");
        } catch (Exception e) {
            logger.error("uploadFile file failed");
            return OperationResult.failOperation("error");
        }
    }

    public void saveFileInfoToDB(FileInfo fileInfo) {

    }


    public void uploadTest() throws Exception {
        File file = new File("/Users/elliot/Downloads/test.jpeg");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        fileChannel.read(byteBuffer);

        TrackerServer trackerServer = trackerServerPool.borrowTrckerServerFromPool();
        StorageClient1 storageClient = new StorageClient1(trackerServer, null);
        String path = storageClient.upload_file1(byteBuffer.array(), "jpeg", null);
        System.out.println(path);
    }

}
