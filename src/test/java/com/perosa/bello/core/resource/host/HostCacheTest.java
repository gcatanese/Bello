package com.perosa.bello.core.resource.host;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class HostCacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostCacheTest.class);


    @Test
    void setAsUnavailable() {

        final String HOST = "host1.perosa.com";

        HostCache.setAsUnavailable(HOST);

        LOGGER.debug(HostCache.getResourceHosts().toString());

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
        assertEquals(4, HostCache.getResourceHosts().size());

    }
}