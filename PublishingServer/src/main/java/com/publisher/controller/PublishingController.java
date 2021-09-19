package com.publisher.controller;

import com.publisher.dto.Subscriber;
import com.publisher.dto.Topic;
import com.publisher.dto.request.SubscriberRequest;
import com.publisher.dto.response.BaseResponse;
import com.publisher.dto.response.PublishResponse;
import com.publisher.dto.response.SubscriberResponse;
import com.publisher.service.PublishingService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PublishingController {
    private static final Logger logger = LoggerFactory.getLogger(PublishingController.class);
    private static final String ORDERSERVICE ="publishingService" ;

    private PublishingService publishingService;
    private int attempts=1;

    public PublishingController(PublishingService publishingService){
       this. publishingService = publishingService;
    }

    @ApiOperation(httpMethod = "POST", value = "", response = SubscriberResponse.class, responseContainer = "")

    @RequestMapping(value = "/subscribe/{topic}" ,
                    consumes = MediaType.APPLICATION_JSON_VALUE ,
                    produces=MediaType.APPLICATION_JSON_VALUE,
                    method = {RequestMethod.POST} )
    public SubscriberResponse subscribe(@RequestBody SubscriberRequest req, @PathVariable String topic) throws MalformedURLException {

        return publishingService.subscribe(req , topic);

    }

    @ApiOperation(httpMethod = "POST", value = "", response = PublishResponse.class, responseContainer = "")

    @RequestMapping(value="/publish/{topic}",
                    consumes = MediaType.APPLICATION_JSON_VALUE ,
                    produces=MediaType.APPLICATION_JSON_VALUE,
                    method = {RequestMethod.POST})
    //@Retry(name = ORDERSERVICE,fallbackMethod = "invokeAfterRetry")
    public PublishResponse publish(@RequestBody Map<String,Object> req, @PathVariable String topic) throws IOException {

       // System.out.println();
        return   publishingService.publish(topic, req);
    }

//    public ResponseEntity<String> invokeAfterRetry(Exception e){
//        attempts=1;
//        return new ResponseEntity<String>("subscriber service is down", HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
}
