package com.example.yoga;

import com.example.yoga.model.Article;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<Article> articles;
    Context context;

    public NewsAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView ivThumbnail;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);

            // 🔗 Set click listener
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Article clickedArticle = articles.get(position);
                    Intent intent = new Intent(context, FullArticleActivity.class);
                    intent.putExtra("url", clickedArticle.url);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.tvTitle.setText(article.title);
        holder.tvDescription.setText(article.description);
        Glide.with(context).load(article.urlToImage).into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
