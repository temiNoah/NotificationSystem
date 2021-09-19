package com.publisher.dto;

import java.net.URL;

public class Subscriber {

    private URL url;
    private Topic topic;


    public Subscriber(URL url, Topic topic  ) {

        this.url = url;
        this.topic=topic;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Subscriber))
            return false;

         return  this.getUrl().equals( ((Subscriber) obj) .getUrl() );
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "url=" + url +
                '}';
    }
}
