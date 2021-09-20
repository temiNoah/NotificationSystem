package com.publisher.config;

import com.publisher.dto.Store;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.internal.InMemoryRetryRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Configuration
public class AppConfig {
    private Store store;

//    @Value("#{'${topics}'.split(',')}")
//    private String[] topics;

//    @Value("#{'${subscribers}'.split(',')}")
//    private String[] subscribers;


    @Bean
    public Store initialize( @Value("#{'${topics}'.split(',')}") String[] topics){
         store = new Store();

        Arrays.asList(topics) .stream().forEach(
                                         topic -> store.add( topic , new HashSet<>())

                                 );
//        for(String topic : topics)
//            store.add(topic,new HashSet<>());


        System.out.println(store.toString());

        return store;
    }
    
    @Bean(name="retryRegistry")
    public RetryRegistry retryRegistry(){

        RetryConfig publishingConfig = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.  ofMillis(100))
                //.retryOnResult(remoteResponse -> remoteResponse.getCode)
                //.retryOnException(e -> (e instanceof RuntimeException))
                .retryExceptions(IOException.class , RuntimeException.class ,Exception.class)
                .build();

        HashMap<String , RetryConfig> config = new HashMap<>();
        config.put("publishingConfig" , publishingConfig);

        return  new InMemoryRetryRegistry(config);
    }

    @Bean(name = "restTemplate")
    public RestTemplate createBean(){
        RestTemplate restTemplate  =new RestTemplate();

        return restTemplate;
    }

}
