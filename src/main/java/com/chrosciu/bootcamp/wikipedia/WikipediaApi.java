package com.chrosciu.bootcamp.wikipedia;

import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikipediaApi {
    //https://en.wikipedia.org/w/
    @GET("api.php?action=query&format=json&list=search")
    Mono<Response> getArticles(@Query("srsearch") String query);
}
