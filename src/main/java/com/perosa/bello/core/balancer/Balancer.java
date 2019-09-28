package com.perosa.bello.core.balancer;

import com.perosa.bello.core.balancer.strategy.RoundRobinBalancer;
import com.perosa.bello.core.balancer.strategy.WeightedRoundRobinBalancer;
import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.channel.ChannelFactory;
import com.perosa.bello.server.InRequest;

public interface Balancer {

    String findTarget(InRequest request);

    static Balancer getInstance() {
        Env env = new Env();

        if (env.getStrategy().equalsIgnoreCase("random")) {
            return new RoundRobinBalancer(SessionCache.make(), ChannelFactory.make());
        } else if (env.getStrategy().equalsIgnoreCase("weightedroundrobin")) {
            return new WeightedRoundRobinBalancer(SessionCache.make(), ChannelFactory.make());
        } else {
            return new RoundRobinBalancer(SessionCache.make(), ChannelFactory.make());
        }
    }
}
