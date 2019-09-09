package com.perosa.bello.core.resource.data;

import com.perosa.bello.core.resource.ResourcePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ResourceCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCache.class);

    private static List<ResourcePool> resourcePools = new ArrayList<>();

    public static List<ResourcePool> getResourcePools() {

        if(resourcePools == null) {
            resourcePools = new ResourceDatastore().load();
        }

        return resourcePools;
    }

    public static void invalidate() {
        resourcePools = null;
    }

}
