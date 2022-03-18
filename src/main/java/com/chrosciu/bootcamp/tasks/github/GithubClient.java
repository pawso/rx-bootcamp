package com.chrosciu.bootcamp.tasks.github;

import com.chrosciu.bootcamp.tasks.github.dto.Branch;
import com.chrosciu.bootcamp.tasks.github.dto.Repository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GithubClient {
    private final GithubApi githubApi;

    public Flux<Repository> getUserRepositories(String username) {
        return githubApi.getUserRepositories(username).flatMapMany(Flux::fromIterable);
    }

    public Flux<Repository> getUsersRepositories(Publisher<String> usernames) {
        return Flux.from(usernames).flatMap(u -> getUserRepositories(u).onErrorResume(t -> Flux.empty()));
    }

    public Flux<Branch> getUserRepositoryBranches(String username, String repo) {
        return githubApi.getUserRepositoryBranches(username, repo).flatMapMany(Flux::fromIterable);
    }

    public Flux<String> getAllUserBranches(String username) {
        return getUserRepositories(username).take(5)
                .flatMap(repository -> getUserRepositoryBranches(username, repository.getName()))//.subscribeOn(Schedulers.elastic()))
                .map(Branch::getName);
    }
}
