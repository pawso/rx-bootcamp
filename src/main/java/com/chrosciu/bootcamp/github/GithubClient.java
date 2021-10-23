package com.chrosciu.bootcamp.github;

import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class GithubClient {
    private final String githubToken = "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";

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
        return null;
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

        Thread.sleep(5000);

        githubClient.dispose();
    }
}
