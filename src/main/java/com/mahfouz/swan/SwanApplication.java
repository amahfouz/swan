package com.mahfouz.swan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This annotation rolls up three annotations:
//
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
//
@SpringBootApplication
public class SwanApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwanApplication.class, args);
    }

}