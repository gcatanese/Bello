package com.perosa.bello.core.resource.host;

import com.perosa.bello.core.resource.ResourceHost;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostDatastoreTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostDatastoreTest.class);


    @Test
    void unmarshal() throws IOException {

        String json = "[\n" +
                "  {\n" +
                "    \"host\": \"host1.perosa.com\",\n" +
                "    \"available\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"host\": \"host2.perosa.com\",\n" +
                "    \"available\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"host\": \"host3.perosa.com\",\n" +
                "    \"available\": \"0\"\n" +
                "  }\n" +
                "]\n";

        List<ResourceHost> list = new HostDatastore().unmarshal(json);

        assertNotNull(list);
        assertEquals(3, list.size());

    }

    @Test
    void getJson() throws IOException {

        String json = new HostDatastore().getJson("src/test/resources/hosts.json");

        LOGGER.info(json);

        assertNotNull(json);

    }

}