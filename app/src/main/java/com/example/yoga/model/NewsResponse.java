package com.example.yoga.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsResponse {
    @SerializedName("articles")
    public List<Article> articles;
}
