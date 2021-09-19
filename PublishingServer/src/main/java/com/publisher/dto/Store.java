package com.publisher.dto;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Store {

    private Map<String, Set<Subscriber>> store;

    public Store( ) {
        this.store = new HashMap<>();
    }

    public Store(Map<String, Set<Subscriber>> store) {
        this.store = store;
    }

    public Map<String, Set<Subscriber>> getStore() {
        return store;
    }

    public void setStore(Map<String, Set<Subscriber>> store) {
        this.store = store;
    }

    public void add(String topic ,Set<Subscriber> subscribers){

        if( isTopicPresent(topic) )

            subscribers.stream().forEach(
                                         subscriber ->  store.get(topic).add(subscriber)
                                       );
        else
            store.put(topic , subscribers);

    }

    private boolean isTopicPresent(String topic){

       return store.keySet().stream()
                      .filter(topicObj -> topicObj.equals(topic) )
                      .findAny()
                      .isPresent();
    }

    @Override
    public String toString() {
        return "Store{" +
                "store : key:" + store.keySet() + " value:"+store.values().toString()+
                '}';
    }

    public Set<Subscriber> getSubscribers(String topic) {

        return  this.store.get(topic);
    }
}
