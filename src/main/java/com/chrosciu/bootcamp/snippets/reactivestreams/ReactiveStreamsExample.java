package com.chrosciu.bootcamp.snippets.reactivestreams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReactiveStreamsExample {
    public static void main(String[] args) {
        MyPublisher publisher = new MyPublisher(100);
        MySubscriber subscriber = new MySubscriber();
        publisher.subscribe(subscriber);
        subscriber.request(5);
        subscriber.request(-5);
//        subscriber.cancel();
        subscriber.request(10);
//        subscriber.request(300);
    }
}
