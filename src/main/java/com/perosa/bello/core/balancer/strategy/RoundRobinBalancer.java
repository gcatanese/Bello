package com.perosa.bello.core.balancer.strategy;

import com.perosa.bello.core.balancer.Balancer;
import com.perosa.bello.core.balancer.CoreBalancer;
import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoundRobinBalancer extends CoreBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobinBalancer.class);

    private static int last = 0;

    public RoundRobinBalancer(SessionCache sessionCache, Channel channel) {
        super(sessionCache, channel);
    }

    public ResourceHost findNext(List<ResourceHost> hosts) {
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
