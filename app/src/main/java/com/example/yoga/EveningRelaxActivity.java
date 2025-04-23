package com.example.yoga;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class EveningRelaxActivity extends AppCompatActivity {

    private RecyclerView posesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evening_relax);

        // Header Setup
        ImageView backBtn = findViewById(R.id.btn_back);
        backBtn.setOnClickListener(v -> finish());

        TextView title = findViewById(R.id.tv_header_title);
        title.setText("Evening Relaxation");

        // Poses List Setup
        posesRecyclerView = findViewById(R.id.rv_poses);
        posesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<YogaPose> poses = new ArrayList<>();
        poses.add(new YogaPose("Child's Pose", "5 mins", R.drawable.childspose, "Relax and release tension"));
        poses.add(new YogaPose("Legs-Up-The-Wall", "7 mins", R.drawable.leg_ups, "Improve circulation"));
        poses.add(new YogaPose("Seated Forward Bend", "4 mins", R.drawable.seated_forward_bend, "Calm the mind"));
        poses.add(new YogaPose("Reclining Butterfly", "6 mins", R.drawable.reclining, "Release hip tension"));
        poses.add(new YogaPose("Corpse Pose", "10 mins", R.drawable.corpse_pose, "Deep relaxation"));

        PoseAdapter adapter = new PoseAdapter(poses);
        posesRecyclerView.setAdapter(adapter);
    }

    private static class YogaPose {
        String name;
        String duration;
        int imageRes;
        String instructions;

        public YogaPose(String name, String duration, int imageRes, String instructions) {
            this.name = name;
            this.duration = duration;
            this.imageRes = imageRes;
            this.instructions = instructions;
        }
    }

    private class PoseAdapter extends RecyclerView.Adapter<PoseAdapter.ViewHolder> {
        private final List<YogaPose> poses;

        public PoseAdapter(List<YogaPose> poses) {
            this.poses = poses;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_pose, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            YogaPose pose = poses.get(position);
            holder.ivPose.setImageResource(pose.imageRes);
            holder.tvName.setText(pose.name);
            holder.tvDuration.setText(pose.duration);
            holder.tvInstructions.setText(pose.instructions);

            holder.btnTryIt.setOnClickListener(v -> {
                // Handle pose-specific intents
                if (pose.name.equals("Corpse Pose")) {
                    Intent intent = new Intent(EveningRelaxActivity.this, DeepRelaxationActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return poses.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivPose;
            TextView tvName, tvDuration, tvInstructions;
            MaterialButton btnTryIt;

            ViewHolder(View itemView) {
                super(itemView);
                ivPose = itemView.findViewById(R.id.iv_pose);
                tvName = itemView.findViewById(R.id.tv_pose_name);
                tvDuration = itemView.findViewById(R.id.tv_duration);
                tvInstructions = itemView.findViewById(R.id.tv_instructions);
                btnTryIt = itemView.findViewById(R.id.btn_try_it);
            }
        }
    }
}