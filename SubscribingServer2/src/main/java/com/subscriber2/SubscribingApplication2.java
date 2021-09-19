package com.subscriber2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.subscriber2.*"})
@EntityScan(basePackages = {"com.subscriber2.*"})
public class SubscribingApplication2 {

    public static void main(String[] args) {

        SpringApplication.run(SubscribingApplication2.class);
    }
}
