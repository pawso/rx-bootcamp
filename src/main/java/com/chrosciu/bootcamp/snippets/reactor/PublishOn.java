package com.chrosciu.bootcamp.snippets.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class PublishOn {
    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Scheduler scheduler = Schedulers.newElastic("E");

        Flux<String> flux = Flux.<String>create(sink -> {
                    log.info("Before sending A");
                    sink.next("A");
                    log.info("After sending A");
                    sink.next("B");
                    log.info("After sending B");
                    sink.complete();
                    log.info("After sending complete");
                })
                .log("Above publishOn")
                .publishOn(scheduler)
                .log("Below publishOn");


        flux.doFinally(st -> latch.countDown()).subscribe(s -> {
                    log.info("Element received: {}", s);
                    saveToDb(s);
                },
                e -> log.warn("Error occured: ", e),
                () -> log.info("Stream completed"));

        latch.await();

        scheduler.dispose();
    }

    @SneakyThrows
    private static void saveToDb(String s) {
        log.info("Before saving: {}", s);
        log.info("Blocking !!!!");
        Thread.sleep(1000);
        log.info("After saving: {}", s);
    }
}
