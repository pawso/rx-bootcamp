package com.chrosciu.bootcamp.tasks.github;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@RequiredArgsConstructor
public class GithubAuthInterceptor implements Interceptor {
    private final String githubToken;

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", "token " + githubToken).build();
        return chain.proceed(authenticatedRequest);
    }
}
