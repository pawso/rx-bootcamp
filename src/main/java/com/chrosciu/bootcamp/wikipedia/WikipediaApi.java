package com.chrosciu.bootcamp.wikipedia;

import reactor.core.publisher.Flux;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikipediaApi {
    //https://en.wikipedia.org/w/
    @GET("api.php?action=query&format=json&list=search")
    Flux<Response> getArticles(@Query("srsearch") String query);
}
