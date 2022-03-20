package com.chrosciu.bootcamp.snippets.reactor;

import reactor.core.publisher.Mono;

public class Threads3 {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("S");
        String s = mono.block();
        process(s);
    }

    private static void process(String s) {
        //
    }
}
