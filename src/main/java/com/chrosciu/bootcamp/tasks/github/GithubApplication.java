package com.chrosciu.bootcamp.tasks.github;

import com.chrosciu.bootcamp.tasks.github.dto.Repository;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import reactor.core.publisher.Flux;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Slf4j
public class GithubApplication {
    private final OkHttpClient client;
    private final Retrofit retrofit;
    private final GithubApi githubApi;
    private final GithubClient githubClient;

    public GithubApplication() {
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(new GithubAuthInterceptor(GithubToken.TOKEN))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .client(client)
                .build();
        githubApi = retrofit.create(GithubApi.class);
        githubClient = new GithubClient(githubApi);
    }

    private void dispose() {
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }

    @SneakyThrows
    private void run() {
//        Flux<Repository> repositories = githubClient.getUserRepositories("chrosciu");
//        repositories.subscribe(r -> log.info("{}", r), e -> log.warn("Error: ", e));

        Flux<String> users = Flux.just("chrosciu2", "chrosciu");
        Flux<Repository> repositoryFlux = githubClient.getUsersRepositories(users);
        repositoryFlux.subscribe(r -> log.info("{}", r), e -> log.warn("Error: ", e));

//        Flux<Branch> branches = githubClient.getUserRepositoryBranches("chrosciu", "lite-rx-api-hands-on");
//        branches.subscribe(b -> log.info("{}", b));
//
//        CountDownLatch latch = new CountDownLatch(1);
//        Flux<String> allUserBranches = githubClient.getAllUserBranches("chrosciu");
//        allUserBranches.doFinally(s -> latch.countDown()).subscribe(b -> log.info("{}", b));
//        latch.await();
    }

    public static void main(String[] args) {
        GithubApplication githubApplication = new GithubApplication();
        try {
            githubApplication.run();
        } finally {
            githubApplication.dispose();
        }
    }
}
