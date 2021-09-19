package com.publisher.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PublishRequest {
    private String topic;
    private String data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
