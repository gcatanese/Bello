package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RandomBalancerTest {

    @Test
    void findNext() {
        List<ResourceHost> hosts = new ArrayList<>();

        RandomBalancer randomBalancer = new RandomBalancer();

        hosts.add(new ResourceHost("localhost1"));
        hosts.add(new ResourceHost("localhost2"));
        hosts.add(new ResourceHost("localhost3"));

        ResourceHost resourceHost = randomBalancer.findNext(hosts);

        assertNotNull(resourceHost);

    }


}