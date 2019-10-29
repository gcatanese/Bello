package com.perosa.bello.core.balancer.strategy;

import com.perosa.bello.core.balancer.Balancer;
import com.perosa.bello.core.balancer.CoreBalancer;
import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.session.SessionCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedRoundRobinBalancer extends CoreBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeightedRoundRobinBalancer.class);

    public WeightedRoundRobinBalancer(SessionCache sessionCache, Channel channel) {
        super(sessionCache, channel);
    }

    public ResourceHost findNext(List<ResourceHost> hosts) {
        ResourceHost resourceHost = null;

        resourceHost = getBucket(hosts).stream()
                .skip(getRandomEntry())
                .findFirst()
                .get();

        return resourceHost;
    }

    int getRandomEntry() {
        return new Random().nextInt(101);

    }

    List<ResourceHost> getBucket(List<ResourceHost> hosts) {
        List<ResourceHost> bucket = new ArrayList<>();

        hosts.stream().forEach(h -> bucket.addAll(getHostEntries(h, h.getWeight())));

        return bucket;
    }

    List<ResourceHost> getHostEntries(ResourceHost host, int weight) {
        List<ResourceHost> list = new ArrayList<>();

        for (int i = 0; i < weight; i++) {
            list.add(host);
        }

        return list;
    }

}
