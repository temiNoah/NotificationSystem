package com.subscriber2.service;

import com.subscriber2.dto.request.Notification2Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Subscriber2Service {

    private static final Logger logger = LoggerFactory.getLogger(Subscriber2Service.class);


    public String printNotification(Notification2Request notificationRequest) {


        logger.info(  String.format("{ \"%s\" : \"%s\"}" , notificationRequest.getData() , notificationRequest.getTopic()    ) );

        return "successfully printed";
    }
}
