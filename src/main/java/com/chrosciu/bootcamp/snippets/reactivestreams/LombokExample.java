package com.chrosciu.bootcamp.snippets.reactivestreams;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LombokExample {
    private String name;

    public static void main(String[] args) {
        LombokExample lombokExample = new LombokExample();
        lombokExample.setName("A");
        System.out.println(lombokExample.getName());
    }
}
