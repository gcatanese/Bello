package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RandomBalancer extends CoreBalancer implements Balancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomBalancer.class);

    ResourceHost findNext(List<ResourceHost> hosts) {

        return hosts.stream()
                .skip((int) (hosts.size() * Math.random()))
                .findAny()
                .get();

    }

}
