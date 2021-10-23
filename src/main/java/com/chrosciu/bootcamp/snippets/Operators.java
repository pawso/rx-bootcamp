package com.chrosciu.bootcamp.snippets;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;

@Slf4j
public class Operators {

    private static Mono<String> lower(String s) {
        return Mono.just(s.toLowerCase());
    }

    public static void main(String[] args) throws Exception {
        Flux<String> flux = Flux.just("A", "B", "C");

        Flux<String> flux2 = Flux.just("D", "E", "F");

        Flux<Long> flux3 = Flux.just(1L, 2L);

//        Flux<String> flux = Flux.<String>create(sink -> {
//            log.info("In sink");
//            sink.next("A");
//            sink.next("B");
//            sink.next("C");
//            sink.complete();
//        });

        Flux<String> lowerCase = flux.map(String::toLowerCase);

        Flux<String> filtered = flux.filter("A"::equals);

        Mono<String> reduced = flux.reduce((s, s2) -> s + s2);

        Flux<String> firstTwo = flux.take(2);
        Flux<String> withoutFirst = flux.skip(1);

        Mono<List<String>> collected = flux.collectList();

        Flux<Tuple2<Long, String>> indexed = flux.index();

        Flux<Long> indexes = indexed.map(Tuple2::getT1);
        Flux<String> values = indexed.map(Tuple2::getT2);

        Flux<Tuple2<Long, String>> elapsed = flux.delayElements(Duration.ofSeconds(1)).elapsed();

        Flux<String> concatenated = Flux.concat(flux, flux2);
        //Flux<String> concatenated = flux.concatWith(flux2);

        Flux<String> zipped = Flux.zip(flux, flux3, (s, aLong) -> s + aLong);

        Flux<String> lowered = flux.flatMap(Operators::lower);




        //lowerCase.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //filtered.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //reduced.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //firstTwo.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //withoutFirst.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //collected.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //indexed.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //indexes.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //values.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //indexed.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //elapsed.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //concatenated.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        //zipped.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));
        lowered.subscribe(s -> log.info("Element received: {}", s), e -> log.warn("Error occured: ", e), () -> log.info("Stream completed"));

        //Thread.sleep(4000);

    }
}
