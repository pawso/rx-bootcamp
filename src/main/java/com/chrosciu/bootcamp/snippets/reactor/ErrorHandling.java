package com.chrosciu.bootcamp.snippets.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ErrorHandling {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B")
                .concatWith(Flux.<String>error(new RuntimeException("blah")).onErrorResume(throwable -> Flux.just(throwable.getClass().getName())))
                .concatWith(Flux.just("C", "D"));

        //Flux<String> repaired = flux.onErrorResume(throwable -> Flux.just(throwable.getClass().getName()));

        flux.subscribe(s -> log.info("Elem: {}", s),
                e -> log.warn("Error: ", e),
                () -> log.info("Completed"));
    }
}
