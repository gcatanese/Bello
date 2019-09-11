package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.SessionCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoundRobinBalancer extends CoreBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobinBalancer.class);

    private static int last = 0;

    public RoundRobinBalancer(SessionCache sessionCache) {
        super(sessionCache);
        LOGGER.debug("Last used #" + last);
    }

    ResourceHost findNext(List<ResourceHost> hosts) {
        ResourceHost resourceHost = null;

        if(last == hosts.size()) {
            last = 0;
        }

        resourceHost = hosts.stream()
                .skip(last)
                .findFirst()
                .get();

        last++;

        return resourceHost;
    }

    void reset() {
        last = 0;
    }

}
