package com.chrosciu.bootcamp;

import com.chrosciu.bootcamp.input.InputUtils;
import reactor.core.publisher.Flux;

public class Application {
    public static void main(String[] args) {
        Flux<String> input = InputUtils.toFlux(System.in);
        input.subscribe(s -> System.out.println(s));
    }
}
