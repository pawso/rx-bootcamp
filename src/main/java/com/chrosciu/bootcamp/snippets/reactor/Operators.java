package com.chrosciu.bootcamp.snippets.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Operators {

    private static String toLowerSync(String s) {
        return s.toLowerCase();
    }

    private static Mono<String> toLowerAsync(String s) {
            return Mono.just(s.toLowerCase());
    }


    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<String> flux2 = Flux.just("D", "E", "F");
        Flux<Integer> flux3 = Flux.just(1, 2, 3);

        //Flux<String> result = flux1.map(s -> toLowerSync(s));

        //Flux<String> result = flux1.flatMap(s -> toLowerAsync(s));

        //Flux<String> result = flux1.filter(s -> !"A".equals(s));

        //Mono<String> result = flux1.reduce((s1, s2) -> s1 + s2);

        //Mono<List<String>> result1 = flux1.collectList();

        //Flux<String> result = result1.flatMapMany(l -> Flux.fromIterable(l));

        //Flux<String> result = flux1.take(2);

        //Flux<String> result = flux1.skip(1);

        //Flux<Tuple2<Long, String>> result =  flux1.index();

        //Flux<String> result = flux1.delayElements(Duration.ofMillis(500));

        //Flux<String> result = flux1.concatWith(flux2);

        //Flux<String> result = flux1.mergeWith(flux2);

        //Flux<String> result = flux1.zipWith(flux3).map(t -> t.getT1() + t.getT2());

        Flux<String> result = flux1.zipWith(flux3, (s, i) -> s + i);

        result.doFinally(signalType -> countDownLatch.countDown()).subscribe(s -> log.info("Elem: {}", s),
                e -> log.warn("Error: ", e),
                () -> log.info("Completed"));

        countDownLatch.await();

        //Thread.sleep(4000);
    }
}
