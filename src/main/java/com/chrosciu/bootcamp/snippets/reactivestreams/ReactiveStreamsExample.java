package com.chrosciu.bootcamp.snippets.reactivestreams;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

@Slf4j
public class ReactiveStreamsExample {

    public static void main(String[] args) {
        Publisher<Long> publisher = new MyPublisher(100);
        MySubscriber subscriber = new MySubscriber();
        log.info("subscribe()");
        publisher.subscribe(subscriber);
        log.info("request(5)");
        subscriber.request(5);
//        log.info("cancel()");
//        subscriber.cancel();
        log.info("request(5)");
        subscriber.request(5);
        log.info("request(200)");
        subscriber.request(200);
//        log.info("request(15)");
//        ((MySubscriber)subscriber).request(15);
        log.info("cancel()");
        subscriber.cancel();
    }
}
