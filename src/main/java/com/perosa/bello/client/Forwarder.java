package com.perosa.bello.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Forwarder {

    private static final Logger LOGGER = LoggerFactory.getLogger(Forwarder.class);

    HttpURLConnection call(String endpoint, String method, Map<String, String> headers, String payload) throws Exception {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {

            URL url = new URL(endpoint);

            if (url.getProtocol().equalsIgnoreCase("https")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }
            //  Set connection properties
            connection.setRequestMethod(method);
            connection.setReadTimeout(3 * 1000);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            setHeaders(connection, headers);

            if (payload != null) {
                OutputStream os = connection.getOutputStream();

                os.write(payload.getBytes(StandardCharsets.UTF_8));

                os.flush();
                os.close();
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                LOGGER.info("200 OK");
            } else {
                throw new RuntimeException("responseCode:" + responseCode + " message:" + connection.getResponseMessage());
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return connection;
    }

    void setHeaders(HttpURLConnection connection, Map<String, String> headers) {
        if (headers.get("Content-Type") != null) {
            connection.setRequestProperty("Content-Type", headers.get("Content-Type"));
        }
        if (headers.get("Accept") != null) {
            connection.setRequestProperty("Accept", headers.get("Accept"));
        }
        if (headers.get("ResourcePool") != null) {
            connection.setRequestProperty("ResourcePool", headers.get("ResourcePool"));
        }
        if (headers.get("Accept-Encoding") != null) {
            connection.setRequestProperty("Accept-Encoding", headers.get("Accept-Encoding"));
        }
        if (headers.get("User-Agent") != null) {
            connection.setRequestProperty("User-Agent", headers.get("User-Agent"));
        }
        if (headers.get("Authorization") != null) {
            connection.setRequestProperty("Authorization", headers.get("Authorization"));
        }
    }
}
