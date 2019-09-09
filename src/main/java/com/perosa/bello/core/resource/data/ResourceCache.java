package com.perosa.bello.core.resource.data;

import com.perosa.bello.core.resource.ResourceHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ResourceCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCache.class);

    private static List<ResourceHost> resourceHosts = null;

    public static List<ResourceHost> getResourceHosts() {

        if(resourceHosts == null) {
            resourceHosts = new ResourceDatastore().load();
        }

        return resourceHosts;
    }

    public static void invalidate() {
        resourceHosts = null;
    }

}
