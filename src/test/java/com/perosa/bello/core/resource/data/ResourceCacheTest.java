package com.perosa.bello.core.resource.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceCacheTest {

    @Test
    void setAsUnavailable() {

        final String HOST = "host1.perosa.com";

        ResourceCache.setAsUnavailable(HOST);

        assertFalse(ResourceCache.getResourceHosts().stream()
        .filter(h -> h.getHost().equals(HOST))
        .findAny().get().isAvailable());
    }

    @Test
    void setAsAvailable() {

        final String HOST = "host3.perosa.com";

        ResourceCache.setAsAvailable(HOST);

        assertTrue(ResourceCache.getResourceHosts().stream()
                .filter(h -> h.getHost().equals(HOST))
                .findAny().get().isAvailable());
    }

    @Test
    void getResourceHosts() {
        assertNotNull(ResourceCache.getResourceHosts());

    }
}