package com.chrosciu.bootcamp.snippets.reactor;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
class MySubscriber implements Subscriber<String> {

    private Subscription subscription;

    public void request(long n) {
        if (subscription != null) {
            subscription.request(n);
        }
    }

    public void cancel() {
        if (subscription != null) {
            subscription.cancel();
        }
        subscription = null;
    }

    @Override
    public void onSubscribe(@NonNull Subscription subscription) {
        log.info("onSubscription: {}", subscription);
        if (this.subscription != null) {
            subscription.cancel();
            return;
        }
        this.subscription = subscription;
    }

    @Override
    public void onNext(@NonNull String s) {
        log.info("onNext: {}", s);
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        log.error("onError: ", throwable);
        subscription = null;
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
        subscription = null;
    }
}
