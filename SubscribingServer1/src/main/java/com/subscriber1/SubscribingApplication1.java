package com.subscriber1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.subscriber1.*"})
@EntityScan(basePackages = {"com.subscriber1.*"})
public class SubscribingApplication1 {
   static ObjectMapper mapper ;

   public static void main(String[] args) {

        SpringApplication.run(SubscribingApplication1.class);
//        mapper = new ObjectMapper();
//        String val= "{ \"message\": { \"msg\" :  {\"ms\" :  {\" m\":\" very great\" } }  } }";   // { msg" : {"msg" : "hello"} }
//       String  v= "{ \"message\":  \"wow\"  }";
//       System.out.println(  m(val)  );
    }
}
