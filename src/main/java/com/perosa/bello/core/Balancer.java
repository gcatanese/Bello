package com.perosa.bello.core;

import com.perosa.bello.core.resource.SessionCache;
import com.perosa.bello.core.resource.channel.ChannelFactory;
import com.perosa.bello.server.InRequest;

public interface Balancer {

    String findTarget(InRequest request);

    static Balancer getInstance() {
        return new RoundRobinBalancer(SessionCache.make(), ChannelFactory.make());
    }
}
