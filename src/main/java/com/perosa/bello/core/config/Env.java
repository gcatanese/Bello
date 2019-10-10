package com.perosa.bello.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Env {

    private static final Logger LOGGER = LoggerFactory.getLogger(Env.class);

    public String getConfig() {
        String config = System.getProperty("config");

        if (config == null || config.isEmpty()) {
            config = "config/";
        }

        if (!config.endsWith("/")) {
            config = config + "/";
        }

        return config;
    }

    public int getPort() {
        String port = System.getProperty("port");

        if (port == null || port.isEmpty()) {
            port = System.getenv("PORT");
        }

        if (port == null || port.isEmpty()) {
            port = "8888";
        }


        return Integer.valueOf(port);
    }

    public int getHealthCheckInterval() {
        String interval = System.getProperty("healthCheckInterval");

        if (interval == null || interval.isEmpty()) {
            interval = "20";
        }


        return Integer.valueOf(interval);
    }

    public String getStrategy() {
        String strategy = System.getProperty("strategy");

        if (strategy == null || strategy.isEmpty()) {
            strategy = "roundrobin";
        }

        return strategy;
    }

    public String getCacheImpl() {
        String cache = System.getProperty("cacheImpl");

        if (cache == null || cache.isEmpty()) {
            cache = "inMemory";
        }

        return cache;
    }

    public String getRedisHost() {
        String host = System.getProperty("redisHost");

        if (host == null || host.isEmpty()) {
            host = "localhost";
        }

        return host;
    }

    public int getRedisPort() {
        String port = System.getProperty("redisPort");

        if (port == null || port.isEmpty()) {
            port = "6379";
        }

        return Integer.valueOf(port);
    }

    @Override
    public String toString() {
        return "conf[" +
                "config:" + getConfig() +
                ",port:" + getPort() +
                ",strategy:" + getStrategy() +
                ",cacheImpl:" + getCacheImpl() +
                "]";
    }
}
