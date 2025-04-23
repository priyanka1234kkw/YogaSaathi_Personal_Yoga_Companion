package com.example.yoga.api;
import com.example.yoga.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("v2/everything")
    Call<NewsResponse> getNews(
            @Query("q") String keyword,
            @Query("apiKey") String apiKey
    );
}
