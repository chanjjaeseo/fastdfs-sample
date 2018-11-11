package com.elliot.fastdfssample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Gloabls {

    @Value("${tracker.host}")
    private String trackerHost;

    public String getTrackerHost() {
        return trackerHost;
    }
}
