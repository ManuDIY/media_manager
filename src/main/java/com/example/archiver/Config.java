package com.example.archiver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAsync
@EnableScheduling
@ConfigurationProperties(prefix="archiver")
public class Config {

    private List<String> servers = new ArrayList<>();

    public List<String> getServers() {
        return this.servers;
    }
}