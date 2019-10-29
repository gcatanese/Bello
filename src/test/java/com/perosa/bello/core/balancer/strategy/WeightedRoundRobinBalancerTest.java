package com.perosa.bello.core.balancer.strategy;

import com.perosa.bello.core.channel.Channel;
import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.session.SessionCache;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeightedRoundRobinBalancerTest {

    @Mock
    SessionCache sessionCache;
    @Mock
    Channel channel;

    @Test
    void findNext() {
        List<ResourceHost> hosts = new ArrayList<>();

        LocalWeightedRoundRobinBalancer rr = new LocalWeightedRoundRobinBalancer(sessionCache, channel);

        hosts.add(new ResourceHost("localhost1", 1, 1, 70));
        hosts.add(new ResourceHost("localhost2", 1, 1, 20));
        hosts.add(new ResourceHost("localhost3", 1, 1, 10));

        ResourceHost resourceHost = rr.findNext(hosts);

        assertNotNull(resourceHost);
        assertEquals("localhost1", resourceHost.getHost());

    }

    @Test
    void getBucket() {
        List<ResourceHost> hosts = new ArrayList<>();
        hosts.add(new ResourceHost("localhost1", 1, 1, 70));
        hosts.add(new ResourceHost("localhost2", 1, 1, 20));
        hosts.add(new ResourceHost("localhost3", 1, 1, 10));


        LocalWeightedRoundRobinBalancer rr = new LocalWeightedRoundRobinBalancer(sessionCache, channel);

        assertNotNull(rr.getBucket(hosts));
        assertEquals(100, rr.getBucket(hosts).size());

    }

    @Test
    void getHostEntries() {
        LocalWeightedRoundRobinBalancer rr = new LocalWeightedRoundRobinBalancer(sessionCache, channel);

        assertEquals(45, rr.getHostEntries(new ResourceHost("localhost"), 45).size());
    }
}

class LocalWeightedRoundRobinBalancer extends WeightedRoundRobinBalancer {
    LocalWeightedRoundRobinBalancer(SessionCache sessionCache, Channel channel) {
        super(sessionCache, channel);
    }

    @Override
    int getRandomEntry() {
        return 45;
    }
}