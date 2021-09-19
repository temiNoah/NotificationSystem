package com.subscriber2.controller;

import com.subscriber2.dto.Response.BaseResponse;
import com.subscriber2.dto.request.Notification2Request;
import com.subscriber2.service.Subscriber2Service;
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
public class Subscriber2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Subscriber2Controller.class);

    @Autowired
    private Subscriber2Service subscriber2Service;



    @ApiOperation(httpMethod = "POST", value = "", response = BaseResponse.class, responseContainer = "")
    @RequestMapping(value="notification",
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces=MediaType.APPLICATION_JSON_VALUE,
            method = {RequestMethod.POST})
    public BaseResponse receivedNotification(@RequestBody Notification2Request notificationRequest){

        String message=  subscriber2Service.printNotification(notificationRequest);

        BaseResponse baseResponse =new BaseResponse();
        baseResponse.setMessage(message);

        return baseResponse;
    }
}