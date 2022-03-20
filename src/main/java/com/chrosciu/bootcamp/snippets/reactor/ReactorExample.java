package com.chrosciu.bootcamp.snippets.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ReactorExample {
    public static void main(String[] args) {
        //Flux<String> flux1 = Flux.just("A", "B", "C");
        //Flux<String> flux1 = Flux.error(new RuntimeException("blah"));
        //MySubscriber subscriber = new MySubscriber();
        //flux1.subscribe(subscriber);
        //subscriber.request(5);
//        Disposable disposable = flux1.subscribe(s -> log.info("Elem: {}", s),
//                e -> log.warn("Error: ", e),
//                () -> log.info("Completed"),
//                subscription -> subscription.request(5));
//        log.info("{}", disposable.isDisposed());
//        disposable.dispose();
        //MyReactorSubscriber subscriber = new MyReactorSubscriber();
        //flux1.subscribe(subscriber);

        Flux<String> flux = Flux.generate(() -> 'A', (character, sink) -> {
            sink.next(Character.toString(character));
            if ('Z' == character) {
                sink.complete();
            }
            return (char)(character + 1);}
        );

        flux.subscribe(s -> log.info("Elem: {}", s));

    }
}
