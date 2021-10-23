package com.chrosciu.bootcamp.snippets;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
class MySubscriber2 extends BaseSubscriber<String> {
    @Override
    public void hookOnSubscribe(Subscription s) {
        s.request(1);
    }

    @Override
    public void hookOnNext(String s) {
        log.info("Element received: {}", s);
        if ("B".equals(s)) {
            cancel();
        } else {
            request(1);
        }
    }
}

@Slf4j
public class ReactorExamples {

    public static void main(String[] args) {
        //Flux<String> flux = Flux.just("A", "B", "C");

        //Flux<String> flux = Flux.error(new RuntimeException("boom"));

        Flux<String> flux1 = Flux.generate(() -> 'A', (context, sink) -> {
            log.info("Function called with {} and {}", context, sink);
            sink.next(String.valueOf(context));
            if ('C' == context) {
                sink.complete();
            }
            return (char)(context + 1);
        });

        Flux<String> flux2 = Flux.create(sink -> {
            log.info("Sink function.called");
            sink.next("A");
            sink.next("B");
            sink.next("C");
            sink.complete();
        });

        //Flux<String> flux = flux1.log();
        Flux<String> flux = flux2.log();

        //flux.subscribe(s -> log.info("Element received: {}", s), e -> {});


        //flux.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));

        Mono<String> mono = Mono.just("A");

        //mono.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));

        Mono<Void> result = mono.then();

        Mono<String> empty = Mono.empty();

        String s = empty.block();

        log.info(s);

        //empty.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));






//        Publisher<String> publisher = flux;
//
//        Subscriber<String> subscriber = new MySubscriber2();
//        publisher.subscribe(subscriber);
    }
}
