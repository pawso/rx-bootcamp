package com.chrosciu.bootcamp.snippets.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ReactorExample {
    @SneakyThrows
    public static void main(String[] args) {
//        Flux<Long> flux = Flux.just(1L, 2L).log();
//        Flux<Long> flux = Flux.error(new RuntimeException("blah"));
//        MySubscriber subscriber = new MySubscriber();
//        flux.subscribe(subscriber);
//        subscriber.request(5);

//        Flux<String> flux = Flux.generate(() -> 'A', (character, sink) -> {
//            sink.next(Character.toString(character));
//            if ('Z' == character) {
//                sink.complete();
//            }
//            return (char) (character + 1);
//        });

//        Flux<Long> flux = Flux.create(sink -> {
//           for (char c = 'A'; c <= 'Z'; ++c) {
//               log.info("toSink: {}", (long)c);
//               sink.next((long)c);
//           }
//           sink.complete();
//        });
//
//        MySubscriber subscriber = new MySubscriber();
//        flux.subscribe(subscriber);
//        subscriber.request(5);
//        subscriber.request(5);



//        flux.subscribe(l -> log.info("onNext: {}", l), t -> log.error("onError: {}", t), () -> log.info("onComplete"));

//        Mono<Long> mono = Mono.fromCallable(() -> {
//           log.info("in callable");
//           return 1L;
//        });
//
//        MySubscriber subscriber = new MySubscriber();
//        mono.subscribe(subscriber);
//        subscriber.request(2);

        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1L)).log();

        Disposable disposable = flux.subscribe(l -> log.info("onNext: {}", l));

        Thread.sleep(5000);

        disposable.dispose();

    }
}
