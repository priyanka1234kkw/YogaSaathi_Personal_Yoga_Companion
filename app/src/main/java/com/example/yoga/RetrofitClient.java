package com.example.yoga;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api-inference.huggingface.co/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Configure Gson to handle complex JSON
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient client = new OkHttpClient.Builder()

                    .addInterceptor(chain -> {
                        Request request = chain.request().newBuilder()
                                .header("Authorization", "Bearer " + BuildConfig.HF_API_KEY)
                                .build();
                        return chain.proceed(request);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson)) // Use configured Gson
                    .build();
        }
        return retrofit;
    }
}