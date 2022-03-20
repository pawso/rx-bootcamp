package com.chrosciu.bootcamp.snippets.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Threads {
    @SneakyThrows
    public static void main(String[] args) {
        Scheduler scheduler = Schedulers.elastic();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        log.info("Before create");
        Flux<String> flux = Flux.create(sink -> {
            log.info("Before elements");
            List<String> elements = getElements();
            for (String elem: elements) {
                log.info("Before sending {}", elem);
                sink.next(elem);
                log.info("After sending {}", elem);
            }
            log.info("Before complete");
            sink.complete();
            log.info("After complete");
        });
        log.info("After create, before subscribe");
        flux.subscribeOn(scheduler).doFinally(st -> countDownLatch.countDown()).subscribe(s -> log.info("Elem: {}", s),
                e -> log.warn("Error: ", e),
                () -> log.info("Completed"));
        log.info("After subscribe");
        countDownLatch.await();
    }

    @SneakyThrows
    private static List<String> getElements() {
        log.info("Blocking !!!!!");
        Thread.sleep(1000);
        return Arrays.asList("A", "B");
    }
}
