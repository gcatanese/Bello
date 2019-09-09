package com.perosa.bello.core.resource.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perosa.bello.core.resource.ResourcePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ResourceDatastore {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceDatastore.class);

    private static List<ResourcePool> resourcePools = new ArrayList<>();

    public List<ResourcePool> load() {

        try {
            this.resourcePools = unmarshal(getJson(getFilePath()));

            LOGGER.info("Available resourcePools: " + resourcePools);

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return resourcePools;

    }

    List<ResourcePool> unmarshal(String json) throws IOException {
        List<ResourcePool> resourcePools = null;

        ObjectMapper objectMapper = new ObjectMapper();

        resourcePools = objectMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .findAndRegisterModules()
                .readValue(json, new TypeReference<List<ResourcePool>>() { });

        return resourcePools;
    }

    String getJson(String filepath) throws IOException {
        String json = "";

        File file = new File(filepath);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filepath);
        } else {
            byte[] b = Files.readAllBytes(file.toPath());
            json = new String(b);
        }

        return json;
    }

    private String getFilePath() {
        return "config/hosts.json";
    }

}
