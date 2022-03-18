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
        //TODO: Implement
        return null;
    }

    public Flux<Repository> getUsersRepositories(Publisher<String> usernames) {
        //TODO: Implement
        return null;
    }

    public Flux<Branch> getUserRepositoryBranches(String username, String repo) {
        //TODO: Implement
        return null;    }

    public Flux<String> getAllUserBranchesNames(String username) {
        //TODO: Implement
        return null;
    }
}
