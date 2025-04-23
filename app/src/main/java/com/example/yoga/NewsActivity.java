package com.example.yoga;
import com.example.yoga.model.Article;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.api.NewsApiService;
import com.example.yoga.api.RetrofitClien;
import com.example.yoga.model.Article;
import com.example.yoga.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    EditText etSearch;
    Button btnSearch;
    RecyclerView rvNews;
    NewsAdapter adapter;
    List<Article> articleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rvNews = findViewById(R.id.rvNews);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NewsAdapter(articleList, this);
        rvNews.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> searchNews(etSearch.getText().toString()));
    }

    private void searchNews(String keyword) {
        String apiKey = BuildConfig.NEWS_API_KEY;
        NewsApiService service = RetrofitClien.getService();

        service.getNews(keyword, apiKey).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    articleList.clear();
                    articleList.addAll(response.body().articles);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NewsActivity.this, "No news found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Failed to load news", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
