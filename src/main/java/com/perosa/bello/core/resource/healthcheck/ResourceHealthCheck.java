package com.perosa.bello.core.resource.healthcheck;

import com.perosa.bello.core.config.Env;
import com.perosa.bello.core.resource.ResourceHost;
import com.perosa.bello.core.resource.host.HostCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class ResourceHealthCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceHealthCheck.class);

    private HealthCheckClient healthCheckClient = null;
    private Env env;

    public ResourceHealthCheck(HealthCheckClient healthCheckClient) {
        this.healthCheckClient = healthCheckClient;
        this.env = new Env();
    }

    public void start() {

        Timer timer = new Timer("healthCheckThread");
        final long INTERVAL = 1000L * getEnv().getHealthCheckInterval();

        TimerTask task = new TimerTask() {
            public void run() {
                runHealthCheck();
            }
        };

        timer.schedule(task, 2000L, INTERVAL);

    }

    void runHealthCheck() {
        HostCache.getResourceHosts().stream()
                .forEach(h -> ping(h));

    }

    void ping(ResourceHost resourceHost) {

        try {
            int responseCode = healthCheckClient.ping(getHealthCheckUrl(resourceHost));

            LOGGER.trace("Ping " + getHealthCheckUrl(resourceHost) + " " + responseCode);

            if (responseCode == 200) {
                HostCache.setAsAvailable(resourceHost.getHost());
            } else {
                HostCache.setAsUnavailable(resourceHost.getHost());
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            HostCache.setAsUnavailable(resourceHost.getHost());
        }
    }

    String getHealthCheckUrl(ResourceHost resourceHost) {
        return getHealthCheckScheme(resourceHost) + "://" + resourceHost.getHost() + resourceHost.getHealthCheck();
    }

    String getHealthCheckScheme(ResourceHost resourceHost) {
        return (resourceHost.getHealthCheckScheme() == null ? "http" : resourceHost.getHealthCheckScheme());
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }
}
