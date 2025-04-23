package com.example.yoga;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HuggingFaceAPI {
    // Use smaller model
    @POST("models/google/flan-t5-base")
    Call<JsonArray> queryModel(@Body JsonObject inputs);
}