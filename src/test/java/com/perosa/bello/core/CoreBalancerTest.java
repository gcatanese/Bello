package com.perosa.bello.core;

import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.SessionCache;
import com.perosa.bello.server.InRequest;
import io.undertow.server.HttpHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CoreBalancerTest {

    @Mock
    SessionCache sessionCache;

    @Test
    void findTarget() {

        InRequest request = new InRequest();
        request.setHost("localhost");
        request.setPayload("todo");

        String target = new LocalCoreBalancer(sessionCache).findTarget(request);

        assertNotNull(target);

    }

    @Test
    void getAvailableHosts() {

        List<ResourceHost> hosts = new ArrayList<>();

        hosts.add(new ResourceHost("localhost1"));
        hosts.add(new ResourceHost("localhost2"));
        hosts.add(new ResourceHost("localhost3", 0));


        List<ResourceHost> list = new LocalCoreBalancer(sessionCache).getAvailableHosts(hosts);

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
            List<ResourceHost> list = new LocalCoreBalancer(sessionCache).getAvailableHosts(hosts);
        });


    }

    @Test
    void get() {
        verify(sessionCache, times(1)).get(isA(String.class));
    }

    @Test
    void put() {
        verify(sessionCache, times(1)).put(isA(String.class), isA(String.class));
    }

}

class LocalCoreBalancer extends CoreBalancer {

    LocalCoreBalancer(SessionCache sessionCache) {
        super(sessionCache);
    }
    ResourceHost findNext(List<ResourceHost> hosts) {
        return null;
    }
}