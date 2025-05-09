package com.example.nrsconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer

public class NrsConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NrsConfigServerApplication.class, args);
    }

}
