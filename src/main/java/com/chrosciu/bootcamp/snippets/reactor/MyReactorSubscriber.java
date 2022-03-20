package com.chrosciu.bootcamp.snippets.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

@Slf4j
class MyReactorSubscriber extends BaseSubscriber<String> {
    @Override
    public void hookOnSubscribe(Subscription s) {
        s.request(1);
    }

    @Override
    public void hookOnNext(String s) {
        log.info("Element received: {}", s);
        if ("X".equals(s)) {
            cancel();
        } else {
            request(1);
        }
    }

    @Override
    public void hookOnComplete() {
        log.info("Completed");
    }
}
