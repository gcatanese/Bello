package com.perosa.bello.core.balancer.strategy;

import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.channel.Channel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RandomBalancerTest {

    @Mock
    SessionCache sessionCache;
    @Mock
    Channel channel;

    @Test
    void findNext() {
        List<ResourceHost> hosts = new ArrayList<>();

        RandomBalancer randomBalancer = new RandomBalancer(sessionCache, channel);

        hosts.add(new ResourceHost("localhost1"));
        hosts.add(new ResourceHost("localhost2"));
        hosts.add(new ResourceHost("localhost3"));

        ResourceHost resourceHost = randomBalancer.findNext(hosts);

        assertNotNull(resourceHost);

    }


}