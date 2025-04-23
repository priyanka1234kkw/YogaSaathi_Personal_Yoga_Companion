package com.example.yoga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    private final List<ActivityModel> activityList;

    public ActivityAdapter(List<ActivityModel> activityList) {
        this.activityList = activityList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTimeText, activityDetailText;

        public ViewHolder(View view) {
            super(view);
            dateTimeText = view.findViewById(R.id.dateTimeText);
            activityDetailText = view.findViewById(R.id.activityDetailText);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityModel activity = activityList.get(position);
        holder.dateTimeText.setText(activity.getDateTime());
        holder.activityDetailText.setText(activity.getActivityDetail());
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }
}
