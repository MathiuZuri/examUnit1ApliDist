package com.example.nrs_prestamo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NrsPrestamoApplication {

    public static void main(String[] args) {

        SpringApplication.run(NrsPrestamoApplication.class, args);
    }

}
