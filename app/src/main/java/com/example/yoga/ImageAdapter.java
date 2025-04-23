package com.example.yoga;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> imagePaths;

    public ImageAdapter(Context context, ArrayList<String> imagePaths) {
        this.context = context;
        this.imagePaths = imagePaths;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
        }
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePaths.get(position));
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }
}
