package com.perosa.bello.core.resource.healthcheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HealthCheckClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckClient.class);


    public HealthCheckClient() {
    }

    public int ping(String endpoint) throws Exception {
        
        int responseCode = 0;

        HttpURLConnection connection = null;

        try {

            URL url = new URL(endpoint);

            if (url.getProtocol().equalsIgnoreCase("https")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }
            //  Set connection properties
            connection.setRequestMethod("GET");
            connection.setReadTimeout(2 * 1000);
            connection.setUseCaches(false);

            responseCode = connection.getResponseCode();

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            responseCode = 500;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return responseCode;
    }

}
