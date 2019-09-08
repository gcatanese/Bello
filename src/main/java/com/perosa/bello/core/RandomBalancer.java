package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RandomBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomBalancer.class);

    private ResourceManager resourceManager;

    public String findTarget(String requestedUrl) {

        List<ResourceHost> list = getResourceManager().getResourcePools();


        return null;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
}
