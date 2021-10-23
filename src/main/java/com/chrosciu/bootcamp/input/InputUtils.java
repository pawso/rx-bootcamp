package com.chrosciu.bootcamp.input;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputUtils {
    public static Flux<String> toFlux(InputStream inputStream) {
        //TODO return stream of lines from given input stream
        return null;
    }
}
