package com.publisher.utils;

import com.publisher.dto.Store;
import com.publisher.dto.Subscriber;
import com.publisher.dto.request.PublishRequest;
import com.publisher.dto.response.PublishResponse;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
public class RestClient {



    @Autowired
    private RetryRegistry retryRegistry;

    @Autowired
    RestTemplate restTemplate ; // = new RestTemplate();
    HttpHeaders httpHeaders;

    public  RestClient(){

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


     }
     @Async
    public CompletableFuture<ResponseEntity<PublishResponse> > postCall(Subscriber subscriber, PublishRequest publishRequest){


        //retryRegistry.retry("");
        HttpEntity<PublishRequest> httpEntity=new HttpEntity<PublishRequest>(publishRequest,httpHeaders);

        String url = subscriber.getUrl().toString();

        return CompletableFuture.completedFuture( restTemplate.postForEntity(url, httpEntity , PublishResponse.class));


    }
}
