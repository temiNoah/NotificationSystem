package com.publisher;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.publisher.dto.Store;
import com.publisher.service.PublishingService;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(classes= com.publisher.PublishingApplication.class)
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)

public class PublishingServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Store store;
//    @MockBean
//    private GreetingService service;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                            .post("/api/publish/weather")
                            .content("{ \"message\": { \"msg\" :  {\"ms\" :  {\" m\":\" very great\" } }  } }")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                         )
                     .andExpect(status().isOk())
                     .andExpect(content().string(containsString(" very great")));
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(store).isNotNull();
    }
}