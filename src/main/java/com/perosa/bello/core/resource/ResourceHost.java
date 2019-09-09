package com.perosa.bello.core.resource;

public class ResourceHost {

    private String host;
    private int available = 1;
    private String healthCheck;

    public ResourceHost() {
    }

    public ResourceHost(String host) {
        this.host = host;
    }

    public ResourceHost(String host, int available) {
        this.host = host;
        this.available = available;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return getAvailable() == 1;
    }

    public String getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck;
    }

    @Override
    public String toString() {
        return "ResourceHost{" +
                "host='" + host + '\'' +
                ", available=" + available +
                '}';
    }
}
