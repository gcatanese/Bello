package com.perosa.bello.core.balancer.strategy;

import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.session.SessionCache;
import com.perosa.bello.core.channel.Channel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoundRobinBalancerTest {

    @Mock
    SessionCache sessionCache;
    @Mock
    Channel channel;

    @Test
    void findNext() {
        List<ResourceHost> hosts = new ArrayList<>();

        RoundRobinBalancer roundRobinBalancer = new RoundRobinBalancer(sessionCache, channel);
        roundRobinBalancer.reset();

        hosts.add(new ResourceHost("localhost1"));
        hosts.add(new ResourceHost("localhost2"));
        hosts.add(new ResourceHost("localhost3"));

        ResourceHost resourceHost = roundRobinBalancer.findNext(hosts);

        assertNotNull(resourceHost);
        assertEquals("localhost1", resourceHost.getHost());

    }

    @Test
    void findAfterNext() {
        List<ResourceHost> hosts = new ArrayList<>();

        RoundRobinBalancer roundRobinBalancer = new RoundRobinBalancer(sessionCache, channel);
        roundRobinBalancer.reset();

        hosts.add(new ResourceHost("localhost1"));
        hosts.add(new ResourceHost("localhost2"));
        hosts.add(new ResourceHost("localhost3"));

        ResourceHost resourceHost = null;
        resourceHost = roundRobinBalancer.findNext(hosts);
        resourceHost = roundRobinBalancer.findNext(hosts);

        assertNotNull(resourceHost);
        assertEquals("localhost2", resourceHost.getHost());

    }

    @Test
    void findAfterRestartQueue() {
        List<ResourceHost> hosts = new ArrayList<>();

        RoundRobinBalancer roundRobinBalancer = new RoundRobinBalancer(sessionCache, channel);
        roundRobinBalancer.reset();

        hosts.add(new ResourceHost("localhost1"));
        hosts.add(new ResourceHost("localhost2"));

        ResourceHost resourceHost = null;
        resourceHost = roundRobinBalancer.findNext(hosts);
        resourceHost = roundRobinBalancer.findNext(hosts);
        resourceHost = roundRobinBalancer.findNext(hosts);

        assertNotNull(resourceHost);
        assertEquals("localhost1", resourceHost.getHost());

    }

}