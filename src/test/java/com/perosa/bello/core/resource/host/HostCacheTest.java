package com.perosa.bello.core.resource.host;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HostCacheTest {

    @Test
    void setAsUnavailable() {

        final String HOST = "host1.perosa.com";

        HostCache.setAsUnavailable(HOST);

        assertFalse(HostCache.getResourceHosts().stream()
        .filter(h -> h.getHost().equals(HOST))
        .findAny().get().isAvailable());
    }

    @Test
    void setAsAvailable() {

        final String HOST = "host3.perosa.com";

        HostCache.setAsAvailable(HOST);

        assertTrue(HostCache.getResourceHosts().stream()
                .filter(h -> h.getHost().equals(HOST))
                .findAny().get().isAvailable());
    }

    @Test
    void getResourceHosts() {
        assertNotNull(HostCache.getResourceHosts());

    }
}