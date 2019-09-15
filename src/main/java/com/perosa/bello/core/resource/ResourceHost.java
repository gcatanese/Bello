package com.perosa.bello.core.resource;

public class ResourceHost {

    private String host;
    private int available = 1;
    private int online = 1;
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

    public ResourceHost(String host, int available, int online) {
        this.host = host;
        this.available = available;
        this.online = online;
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

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public boolean isOnline() {
        return getOnline() == 1;
    }

    @Override
    public String toString() {
        return "ResourceHost{" +
                "host='" + host + '\'' +
                ", available=" + available +
                ", online=" + online +
                '}';
    }
}
