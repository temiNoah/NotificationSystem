package com.subscriber1.service;

import com.subscriber1.dto.request.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberService.class);

    public String printNotification(NotificationRequest notificationRequest)
       {

           logger.info(  String.format("{ \"%s\" : \"%s\"}" ,notificationRequest.getData(), notificationRequest.getTopic() ) );

           return "successfully printed";
       }
}
