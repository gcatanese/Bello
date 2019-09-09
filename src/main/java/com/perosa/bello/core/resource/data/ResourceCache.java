package com.perosa.bello.core.resource.data;

import com.perosa.bello.core.resource.ResourceHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

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

    public static void setAsUnavailable(String host) {
        LOGGER.warn("setAsUnavailable " + host);
        setStatus(host, 0);
    }

    public static void setAsAvailable(String host) {
        setStatus(host, 1);
    }

    static void setStatus(String host, int status) {
        Optional<ResourceHost> resourceHost = getResourceHosts().stream()
                .filter(h -> h.getHost().equals(host))
                .findAny();

        if(resourceHost.isPresent()) {
            resourceHost.get().setAvailable(status);
        }
    }


}
