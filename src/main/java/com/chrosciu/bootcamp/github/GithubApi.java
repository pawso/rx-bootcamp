package com.chrosciu.bootcamp.github;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubApi {
    @GET("users/{username}/repos")
    Mono<List<Repository>> getUserRepositories(@Path("username") String username);

    @GET("repos/{username}/{repo}/branches")
    Mono<List<Branch>> getUserRepositoryBranches(@Path("username") String username, @Path("repo") String repo);

}
