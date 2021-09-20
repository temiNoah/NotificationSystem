package com.publisher.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publisher.controller.PublishingController;
import com.publisher.dto.Store;
import com.publisher.dto.Subscriber;
import com.publisher.dto.Topic;
import com.publisher.dto.request.PublishRequest;
import com.publisher.dto.request.SubscriberRequest;
import com.publisher.dto.response.PublishResponse;
import com.publisher.dto.response.SubscriberResponse;
import com.publisher.utils.RestClient;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

@Service
public class PublishingService {

    private static final Logger logger = LoggerFactory.getLogger(PublishingService.class);

        @Autowired
        private Store store;

        @Autowired
        private RestClient restClient;

        @Autowired
        private RetryRegistry retryRegistry;

        private ObjectMapper mapper ;

        public PublishingService(){
            mapper=new ObjectMapper();
        }

        public SubscriberResponse subscribe(SubscriberRequest subscriberRequest , String topic) throws MalformedURLException {

            URL url =new URL(subscriberRequest.getUrl());
            Topic topicObj = new Topic(topic);
            Subscriber subscriber = new Subscriber(url,topicObj);

            Set<Subscriber> subscriberSet = new HashSet<>();
            subscriberSet.add(subscriber);

            store.add(topic,subscriberSet);

            SubscriberResponse subscriberResponse= new SubscriberResponse();
            subscriberResponse .setTopic(topic);
            subscriberResponse .setUrl(subscriber.getUrl().toString());

            return subscriberResponse;

        }

        public PublishResponse publish(String topic, Map<String, Object> req) throws IOException{


                    PublishResponse publishResponse= new PublishResponse();

                    //System.out.println(req.values().stream().findFirst().get());
                    String notification;
                    String data =  req.values().stream().findFirst().get().toString();
                    if(data.contains("="))
                        data = data.replaceAll("=" , ":");
                    if(data.contains("{") && data.contains("}"))
                        notification = fetchMessage(data);
                    else
                        notification= data;

                    PublishRequest publishRequest=new PublishRequest();
                    publishRequest.setTopic(topic);
                    publishRequest.setData(notification);


                    Set<Subscriber> subscribers = store.getSubscribers( publishRequest.getTopic());
                    //publish to subscribers
                     subscribers.stream().forEach( subscriber ->
                                                    {
                                                        try {
                                                            Retry retry = retryRegistry.retry("retryRegistry","publishingConfig");
                                                            Supplier<CompletableFuture<ResponseEntity<PublishResponse>>>  supplier= ()-> restClient.postCall(subscriber,publishRequest);
                                                            Supplier<CompletableFuture<ResponseEntity<PublishResponse>>>  retrySupplier= Retry.decorateSupplier(retry,supplier);
                                                            CompletableFuture<ResponseEntity<PublishResponse>> p= retrySupplier.get();//   restClient.postCall( subscriber,publishRequest);



                                                            logger.info(String.format("{ \"%s \" : \" %s \"}" ,p.get().getBody().getData(),p.get().getBody().getTopic()));
                                                        } catch (InterruptedException e) {
                                                            logger.error(e.getMessage());
                                                            e.printStackTrace();
                                                        } catch (ExecutionException e) {
                                                            logger.error(e.getMessage());
                                                            e.printStackTrace();
                                                        }catch (Exception e){
                                                            logger.error(e.getMessage());
                                                            e.printStackTrace();
                                                        }
                                                        //return null;
                                            }
                                 );

               publishResponse.setData(notification);publishResponse.setTopic(topic);
        return publishResponse;
    }


    /***
     *
     * @param payload, eg payload:"{ \"message\": { \"message\" :  {\"message\" :  {\" message\":\"today's weather report :R.D :10c\" } }  } }"
     * @return
     */
    private String fetchMessage(String payload) throws IOException{

        if( !isNested(payload))
            return  fetchMessageHelper(payload);

        return  fetchMessage( payload.substring(payload.indexOf(":")+1, payload.lastIndexOf("}"))   );

    }

    private  boolean isNested(String g){

        return g.contains(":") ? g.split(":").length > 2 : false;
    }

    /***
     *
     * @param nestedData  , eg  { notification : today's weather report(10C) }
     * @return
     */
    private  String fetchMessageHelper(String nestedData) throws IOException{

            //format string to json , double quote
            nestedData =  "{\" " + nestedData.substring(nestedData.indexOf("{")+1, nestedData.indexOf(":")) + "\" : \" " + nestedData.substring(nestedData.indexOf(":")+1,nestedData.indexOf("}")) + " \"}";
        Map<String, String> map= mapper.readValue(nestedData,Map.class);

            return    map.values().stream()  .findFirst().get();


    }
}
