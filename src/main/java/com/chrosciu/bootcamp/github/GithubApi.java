package com.chrosciu.bootcamp.github;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;


public interface GithubApi {
    @GET("users/{username}/repos")
    Mono<List<Repository>> getUserRepositories(@Path("username") String username);

    @GET("repos/{username}/{repo}/branches")
    Mono<List<Branch>> getUserRepositoryBranches(@Path("username") String username, @Path("repo") String repo);

    @GET("/search/repositories")
    Flux<QueryResult> getRepositories(@Query("q") String query);
}
