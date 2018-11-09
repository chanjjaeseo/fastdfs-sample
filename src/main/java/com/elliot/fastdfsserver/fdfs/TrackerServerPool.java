package com.elliot.fastdfsserver.fdfs;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TrackerServerPool {

    private static Logger logger = LoggerFactory.getLogger(TrackerServerPool.class);

    private static final String FASTDFS_CONFIG_PATH = null;

    private static GenericObjectPool<TrackerServer> trackerServerPool;

    private static int maxStorageConnection = 3;

    static {
        try {
            // 加载配置文件
            ClientGlobal.initByProperties(FASTDFS_CONFIG_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        if(logger.isDebugEnabled()){
            logger.debug("ClientGlobal configInfo: {}", ClientGlobal.configInfo());
        }

        // Pool配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        initPool(poolConfig);

        trackerServerPool = new GenericObjectPool<>(new TrackerServerFactory(), poolConfig);
    }

    private static void initPool(GenericObjectPoolConfig poolConfig) {
        poolConfig.setMinIdle(2);
        if(maxStorageConnection > 0){
            poolConfig.setMaxTotal(maxStorageConnection);
        }
    }

    public static GenericObjectPool<TrackerServer> getObjectPool(){
        return trackerServerPool;
    }

    public TrackerServer getTrckerServerFromPool() {
        TrackerServer trackerServer = null;
        try {
            trackerServer =  getObjectPool().borrowObject();
        } catch (Exception e) {
            logger.error("can`t get tracker server from object pool");
        }
        return trackerServer;
    }

}
