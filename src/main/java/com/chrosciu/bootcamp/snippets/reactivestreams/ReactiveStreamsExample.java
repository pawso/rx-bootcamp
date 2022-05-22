package com.chrosciu.bootcamp.snippets.reactivestreams;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

@Slf4j
public class ReactiveStreamsExample {
    public static void main(String[] args) {
        Publisher<Long> publisher = new MyPublisher(20);
        MySubscriber subscriber = new MySubscriber();
        publisher.subscribe(subscriber);
        subscriber.request(5);
        subscriber.request(-2);
        subscriber.request(100);
    }
}
