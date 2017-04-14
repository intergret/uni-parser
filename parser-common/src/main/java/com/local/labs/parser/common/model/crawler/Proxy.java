package com.local.labs.parser.common.model.crawler;

import java.util.Date;

public class Proxy {

    public enum Provider {
        AMAZON, WDJ_ADSL, SSH_TUNNEL, PROXY_BONANZA, UNKNOWN;
    }

    private int id;

    private String host;

    private int port;

    private String scheme;

    private Provider provider;

    private int usedCount;

    private int failedCount;

    private Date lastUsedTime;

    private double healthRatio;

    private String username;

    private String password;

    private String region;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public Date getLastUsedTime() {
        return lastUsedTime;
    }

    public void setLastUsedTime(Date lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public double getHealthRatio() {
        return healthRatio;
    }

    public void setHealthRatio(double healthRatio) {
        this.healthRatio = healthRatio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void increaseUsedCount() {
        this.usedCount++;
        calculateHealthRatio();
    }

    public void increaseFailedCount() {
        this.failedCount++;
        calculateHealthRatio();
    }

    private void calculateHealthRatio() {
        if (usedCount == 0 || failedCount == 0) {
            healthRatio = 1;
        } else {
            if (failedCount > usedCount) {
                healthRatio = 0;
            } else {
                healthRatio = 1 - failedCount * 1.0 / usedCount;
            }
        }
    }

    @Override
    public String toString() {
        return "Proxy{" +
            "id=" + id +
            ", host='" + host + '\'' +
            ", port=" + port +
            ", scheme='" + scheme + '\'' +
            ", provider=" + provider +
            ", usedCount=" + usedCount +
            ", failedCount=" + failedCount +
            ", lastUsedTime=" + lastUsedTime +
            ", healthRatio=" + healthRatio +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", region='" + region + '\'' +
            '}';
    }
}
