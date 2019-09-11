package com.perosa.bello.core.balancer.strategy;

import com.perosa.bello.core.balancer.Balancer;
import com.perosa.bello.core.balancer.CoreBalancer;
import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RandomBalancer extends CoreBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomBalancer.class);

    public RandomBalancer(SessionCache sessionCache, Channel channel) {
        super(sessionCache, channel);
    }

    public ResourceHost findNext(List<ResourceHost> hosts) {

        return hosts.stream()
                .skip((int) (hosts.size() * Math.random()))
                .findAny()
                .get();

    }

}
