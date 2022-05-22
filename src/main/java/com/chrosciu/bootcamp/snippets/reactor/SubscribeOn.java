package com.chrosciu.bootcamp.snippets.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class SubscribeOn {
    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Scheduler scheduler = Schedulers.newElastic("E");

        Flux<String> flux = Flux.<String>create(sink -> {
                    log.info("Before sending A");
                    sink.next(readFromDb());
                    log.info("After sending A");
                    sink.next(readFromDb());
                    log.info("After sending B");
                    sink.complete();
                    log.info("After sending complete");
                })
                .log("Above subscribeOn")
                .subscribeOn(scheduler)
                .log("Below subscribeOn");


        flux.doFinally(s -> countDownLatch.countDown()).subscribe(
                s -> log.info("Element received: {}", s),
                e -> log.warn("Error occured: ", e),
                () -> log.info("Stream completed")
        );

        countDownLatch.await();

        scheduler.dispose();

    }

    @SneakyThrows
    private static String readFromDb() {
        log.info("Before reading");
        log.info("Blocking !!!!");
        Thread.sleep(1000);
        String result = "X";
        log.info("After reading");
        return result;
    }
}
