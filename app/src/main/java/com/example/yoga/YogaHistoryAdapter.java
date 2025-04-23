package com.example.yoga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YogaHistoryAdapter extends RecyclerView.Adapter<YogaHistoryAdapter.ViewHolder> {

    private List<YogaSession> sessionList;

    public YogaHistoryAdapter(List<YogaSession> sessionList) {
        this.sessionList = sessionList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, completedText;


        public ViewHolder(View view) {
            super(view);
            dateText = view.findViewById(R.id.dateText);
            completedText = view.findViewById(R.id.completedText);
        }
    }

    @NonNull
    @Override
    public YogaHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_yoga_history, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaHistoryAdapter.ViewHolder holder, int position) {
        YogaSession session = sessionList.get(position);
        holder.dateText.setText("Date: " + session.getDate());
        holder.completedText.setText("Completed: " + session.getCompleted());
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }
}
