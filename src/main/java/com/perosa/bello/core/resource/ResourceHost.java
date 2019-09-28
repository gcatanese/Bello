package com.perosa.bello.core.resource;

public class ResourceHost {

    private String host;
    private int available = 1;
    private int online = 1;
    private String healthCheck;
    private int weight;
    private String healthCheckScheme;

    public ResourceHost() {
    }

    public ResourceHost(String host) {
        this.host = host;
    }

    public ResourceHost(String host, int available) {
        this(host);
        this.available = available;
    }

    public ResourceHost(String host, int available, int online) {
        this(host, available);
        this.online = online;
    }

    public ResourceHost(String host, int available, int online, int weight) {
        this(host, available, online);
        this.weight = weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHealthCheckScheme() {
        return healthCheckScheme;
    }

    public void setHealthCheckScheme(String healthCheckScheme) {
        this.healthCheckScheme = healthCheckScheme;
    }

    @Override
    public String toString() {
        return "ResourceHost{" +
                "host='" + host + '\'' +
                ", available=" + available +
                ", online=" + online +
                ", weight=" + weight +
                '}';
    }
}
