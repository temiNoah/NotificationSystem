package com.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.publisher.*"})
@EntityScan(basePackages = {"com.publisher.*"})
public class PublishingApplication {

    public static void main(String[] args) {

        SpringApplication.run(PublishingApplication.class);
    }
}
