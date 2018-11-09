package com.elliot.fastdfsserver.service;

import com.elliot.fastdfsserver.bean.OperationResult;
import com.elliot.fastdfsserver.fdfs.TrackerServerPool;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileHelper {

    @Autowired
    private TrackerServerPool trackerServerPool;

    public OperationResult uploadFile() {
        TrackerServer trackerServer = trackerServerPool.getTrckerServerFromPool();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return null;
    }

}
