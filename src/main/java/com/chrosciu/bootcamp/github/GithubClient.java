package com.chrosciu.bootcamp.github;

import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class GithubClient {
    private static CountDownLatch latch = new CountDownLatch(1);

    private final OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor(new GithubAuthInterceptor(GithubToken.TOKEN))
            .build();

    private final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .client(client)
                .build();

    private final GithubApi githubApi = retrofit.create(GithubApi.class);

    public Flux<Repository> getUserRepositories(String username) {
        return githubApi.getUserRepositories(username).flatMapMany(Flux::fromIterable);
    }

    public Flux<Branch> getUserRepositoryBranches(String username, String repo) {
        return githubApi.getUserRepositoryBranches(username, repo).flatMapMany(Flux::fromIterable);
    }

    public Flux<String> getAllUserBranches(String username) {
        return getUserRepositories(username).take(5)
                .flatMap(repository -> getUserRepositoryBranches(username, repository.getName()).subscribeOn(Schedulers.elastic()))
                .map(Branch::getName)
                .doFinally(s -> latch.countDown());
                //.distinct();
    }

    public void dispose() {
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }

    public static void main(String[] args) throws Exception {
        GithubClient githubClient = new GithubClient();
//        Flux<Repository> repositories = githubClient.getUserRepositories("chrosciu");
//        repositories.subscribe(r -> log.info("{}", r));

//        Flux<Branch> branches = githubClient.getUserRepositoryBranches("chrosciu", "lite-rx-api-hands-on");
//        branches.subscribe(b -> log.info("{}", b));

        Flux<String> branches = githubClient.getAllUserBranches("chrosciu");
        branches.subscribe(b -> log.info("{}", b));

        latch.await();

        githubClient.dispose();
    }
}
