package com.subscriber1.controller;

import com.subscriber1.dto.request.NotificationRequest;
import com.subscriber1.dto.response.BaseResponse;
import com.subscriber1.service.SubscriberService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SubscriberController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberController.class);

    @Autowired
          private SubscriberService subscriberService;



            @ApiOperation(httpMethod = "POST", value = "", response = BaseResponse.class, responseContainer = "")
            @RequestMapping(value="notification",
                            consumes = MediaType.APPLICATION_JSON_VALUE ,
                            produces=MediaType.APPLICATION_JSON_VALUE,
                            method = {RequestMethod.POST})
          public BaseResponse receivedNotification(@RequestBody NotificationRequest notificationRequest){

                 String message=  subscriberService.printNotification(notificationRequest);

                 BaseResponse baseResponse =new BaseResponse();
                 baseResponse.setTopic(notificationRequest.getTopic());
                 baseResponse.setData(notificationRequest.getData());


                 return baseResponse;
          }
}
