package com.example.nrsregistryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NrsRegistryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NrsRegistryServerApplication.class, args);
    }

}
