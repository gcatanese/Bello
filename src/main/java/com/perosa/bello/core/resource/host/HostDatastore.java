package com.perosa.bello.core.resource.host;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perosa.bello.core.resource.ResourceHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class HostDatastore {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostDatastore.class);

    public List<ResourceHost> load() {

        List<ResourceHost> resourceHosts = new ArrayList<>();

        try {
            resourceHosts = unmarshal(getJson(getFilePath()));

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return resourceHosts;

    }

    List<ResourceHost> unmarshal(String json) throws IOException {
        List<ResourceHost> resourceHosts = null;

        ObjectMapper objectMapper = new ObjectMapper();

        resourceHosts = objectMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .findAndRegisterModules()
                .readValue(json, new TypeReference<List<ResourceHost>>() {
                });

        return resourceHosts;
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
