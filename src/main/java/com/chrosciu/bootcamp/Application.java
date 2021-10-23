package com.chrosciu.bootcamp;

import com.chrosciu.bootcamp.input.InputUtils;
import reactor.core.publisher.Flux;

public class Application {
    public static void main(String[] args) {
        //private GithubApi
        //private WikpediaApi

        Flux<String> input = InputUtils.toFlux(System.in);

        //run query from wikipedia and github, merge results (titles of articles and repo names) and display to console
        

        //input.subscribe(s -> System.out.println(s));
    }
}
