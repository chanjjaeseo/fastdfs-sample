package com.elliot.fastdfssample.fdfs;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public TrackerServer create() throws Exception {
        TrackerClient client = new TrackerClient();
        TrackerServer server = client.getConnection();
        if(server == null) {
            logger.error("can`t connect to tracker server, check you configuration");
        }
        return server;
    }

    @Override
    public PooledObject<TrackerServer> wrap(TrackerServer trackerServer) {
        return new DefaultPooledObject<>(trackerServer);
    }
}
