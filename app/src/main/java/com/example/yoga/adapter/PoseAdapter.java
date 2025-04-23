package com.example.yoga.adapter;

import android.content.Context;
import android.graphics.Color; // ✅ Fix: For Color.parseColor
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // ✅ Fix: Button import
import android.widget.TextView;

import androidx.annotation.NonNull; // ✅ Fix: @NonNull import
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.Pose;
import com.example.yoga.R;

import java.util.List;

public class PoseAdapter extends RecyclerView.Adapter<PoseAdapter.PoseViewHolder> {

    private List<Pose> poseList;

    public PoseAdapter(List<Pose> poseList) {
        this.poseList = poseList;
    }

    @NonNull
    @Override
    public PoseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pos, parent, false);
        return new PoseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoseViewHolder holder, int position) {
        Pose pose = poseList.get(position);

        holder.name.setText(pose.getName());
        holder.duration.setText(pose.getDuration());
        holder.description.setText(pose.getDescription());
        holder.benefit.setText("💡 " + pose.getBenefit());

        if (pose.isTried()) {
            holder.triedButton.setText("✓ Tried");
            holder.triedButton.setBackgroundColor(Color.parseColor("#388E3C"));
        } else {
            holder.triedButton.setText("Mark as Tried");
            holder.triedButton.setBackgroundColor(Color.parseColor("#FFC107"));
        }

        holder.triedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pose.setTried(!pose.isTried());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return poseList.size();
    }

    static class PoseViewHolder extends RecyclerView.ViewHolder {
        TextView name, duration, description, benefit;
        Button triedButton;

        public PoseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvPoseName);
            duration = itemView.findViewById(R.id.tvDuration);
            description = itemView.findViewById(R.id.tvDescription);
            benefit = itemView.findViewById(R.id.tvBenefit);
            triedButton = itemView.findViewById(R.id.btnTried);
        }
    }
}
