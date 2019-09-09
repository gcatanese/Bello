package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CoreBalancerTest {

    @Test
    void getAvailableHosts() {

        List<ResourceHost> hosts = new ArrayList<>();

        hosts.add(new ResourceHost("localhost1"));
        hosts.add(new ResourceHost("localhost2"));
        hosts.add(new ResourceHost("localhost3", 0));


        List<ResourceHost> list = new LocalCoreBalancer().getAvailableHosts(hosts);

        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    void noAvailableHosts() {

        List<ResourceHost> hosts = new ArrayList<>();

        hosts.add(new ResourceHost("localhost1", 0));
        hosts.add(new ResourceHost("localhost2", 0));
        hosts.add(new ResourceHost("localhost3", 0));


        Assertions.assertThrows(RuntimeException.class, () -> {
            List<ResourceHost> list = new LocalCoreBalancer().getAvailableHosts(hosts);
        });


    }


}

class LocalCoreBalancer extends CoreBalancer {
    ResourceHost findNext(List<ResourceHost> hosts) {
        return null;
    }
}