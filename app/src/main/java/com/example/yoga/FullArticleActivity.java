package com.example.yoga;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class FullArticleActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_article);

        webView = findViewById(R.id.webView);

        // Enable JavaScript (optional but often required for modern news sites)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Keep navigation within the WebView
        webView.setWebViewClient(new WebViewClient());

        // Get the URL from the intent
        String url = getIntent().getStringExtra("url");

        if (url != null) {
            webView.loadUrl(url);
        }
    }
}
