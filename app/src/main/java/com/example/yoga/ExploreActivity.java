package com.example.yoga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // ✅ Make sure this is added

import androidx.appcompat.app.AppCompatActivity;

public class ExploreActivity extends AppCompatActivity {

    Button btnHistory, btnGallery, btnNews, btnTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_page);

        btnHistory = findViewById(R.id.btn_history);
        btnGallery = findViewById(R.id.btn_gallery);
        btnNews = findViewById(R.id.btn_news);
        btnTips = findViewById(R.id.btn_tips);

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));

        btnGallery.setOnClickListener(v ->
                startActivity(new Intent(this, UploadPhotoActivity.class)));
        btnNews.setOnClickListener(v ->
                startActivity(new Intent(this, NewsActivity.class)));

    }
}
