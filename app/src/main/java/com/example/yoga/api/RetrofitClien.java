package com.example.yoga.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClien {
    private static final String BASE_URL = "https://newsapi.org/";
    private static Retrofit retrofit;

    public static NewsApiService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NewsApiService.class);
    }
}
