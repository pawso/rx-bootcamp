package com.chrosciu.bootcamp.snippets.rxjava2;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RxJava2Example {
    public static void main(String[] args) {
        Single<String> single = Single.just("A");
        single.subscribe(s -> log.info("{}", s), e -> log.warn("Error: ", e));
        single = Single.error(new RuntimeException("boom"));
        single.subscribe(s -> log.info("{}", s), e -> log.warn("Error: ", e));

        Completable completable = Completable.complete();
        completable.subscribe(() -> log.info("Completed"), e -> log.warn("Error: ", e));
        completable = Completable.error(new RuntimeException("boom"));
        completable.subscribe(() -> log.info("Completed"), e -> log.warn("Error: ", e));

        Maybe<String> maybe = Maybe.just("A");
        maybe.subscribe(s -> log.info("{}", s), e -> log.warn("Error: ", e), () -> log.info("Completed"));
        maybe = Maybe.error(new RuntimeException("boom"));
        maybe.subscribe(s -> log.info("{}", s), e -> log.warn("Error: ", e), () -> log.info("Completed"));
        maybe = Maybe.empty();
        maybe.subscribe(s -> log.info("{}", s), e -> log.warn("Error: ", e), () -> log.info("Completed"));

        Observable<String> observable = Observable.just("A", "B");
        observable.subscribe(s -> log.info("{}", s), e -> log.warn("Error: ", e), () -> log.info("Completed"));

        Flowable<String> flowable = Flowable.just("A", "B");
        flowable.subscribe(s -> log.info("{}", s), e -> log.warn("Error: ", e), () -> log.info("Completed"));

    }
}
