package com.chrosciu.bootcamp.snippets.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Logging {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C");
        Flux<String> result = flux.log().skip(1).map(String::toLowerCase);
//        result.subscribe(s -> log.info("Elem: {}", s),
//                e -> log.warn("Error: ", e),
//                () -> log.info("Completed"));
        result.subscribe();
    }
}
