package com.perosa.bello.core.resource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceManagerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceManagerTest.class);


    @Test
    void unmarshal() throws IOException {

        String json = "[\n" +
                "  {\n" +
                "    \"path\": \"/\",\n" +
                "    \"hosts\": [\n" +
                "      {\n" +
                "        \"host\": \"host1.perosa.com\",\n" +
                "        \"status\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"host\": \"host2.perosa.com\",\n" +
                "        \"status\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"host\": \"host3.perosa.com\",\n" +
                "        \"status\": \"0\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        List<ResourcePool> list = new ResourceManager().unmarshal(json);

        assertNotNull(list);
        assertEquals(1, list.size());

    }

    @Test
    void getJson() throws IOException {

        String json = new ResourceManager().getJson("src/test/resources/hosts.json");

        LOGGER.info(json);

        assertNotNull(json);

    }

}