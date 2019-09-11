package com.perosa.bello.core.balancer;

import com.perosa.bello.core.balancer.strategy.RoundRobinBalancer;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.channel.ChannelFactory;
import com.perosa.bello.server.InRequest;

public interface Balancer {

    String findTarget(InRequest request);

    static Balancer getInstance() {
        return new RoundRobinBalancer(SessionCache.make(), ChannelFactory.make());
    }
}
