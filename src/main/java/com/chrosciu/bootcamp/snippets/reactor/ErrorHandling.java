package com.chrosciu.bootcamp.snippets.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
public class ErrorHandling {

    static Flux<String> errorStream = Flux.just("A", "B", "C")
            .concatWith(Flux.error(new RuntimeException("boom")))
            .concatWith(Flux.just("D"));

    static class BasicHandle {
        public static void main(String[] args) {
            errorStream.subscribe(s -> log.info("Element received: {}", s),
                    e -> log.warn("Error occured: ", e),
                    () -> log.info("Stream completed"));
        }
    }

    static class StaticFallback {
        public static void main(String[] args) {
            //Flux<String> withStaticFallback = errorStream.onErrorReturn("Fallback");
            Flux<String> withStaticFallback = errorStream
                    .onErrorReturn(throwable -> throwable instanceof IOException, "IO Fallback")
                    .onErrorReturn(throwable -> throwable instanceof RuntimeException, "Runtime Fallback");

            withStaticFallback.subscribe(s -> log.info("Element received: {}", s),
                    e -> log.warn("Error occured: ", e),
                    () -> log.info("Stream completed"));
        }
    }

    static class DynamicFallback {
        public static void main(String[] args) {
            Flux<String> withDynamicFallback = errorStream
                    .onErrorResume(throwable -> throwable instanceof RuntimeException, throwable -> Flux.just("Y", "Z", throwable.getMessage()))
                    .onErrorResume(throwable -> throwable instanceof IOException, throwable -> Flux.just("I"));

            withDynamicFallback.subscribe(s -> log.info("Element received: {}", s),
                    e -> log.warn("Error occured: ", e),
                    () -> log.info("Stream completed"));
        }
    }
}
