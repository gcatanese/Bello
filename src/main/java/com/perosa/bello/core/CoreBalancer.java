package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.ResourcePool;
import com.perosa.bello.core.resource.data.ResourceCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CoreBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreBalancer.class);

    abstract ResourceHost findNext(List<ResourceHost> hosts);

    public String findTarget(String host) {

        List<ResourcePool> list = ResourceCache.getResourcePools();

        List<ResourceHost> availableHosts = getAvailableHosts(list.get(0).getResourceHostList());

        ResourceHost resourceHost = findNext(availableHosts);

        return resourceHost.getHost();
    }

    List<ResourceHost> getAvailableHosts(List<ResourceHost> list) {
        List<ResourceHost> hosts = list.stream()
                .filter(h -> h.isAvailable())
                .collect(Collectors.toList());

        if (hosts.isEmpty()) {
            throw new RuntimeException(("No targets available"));
        }

        return hosts;
    }

}
