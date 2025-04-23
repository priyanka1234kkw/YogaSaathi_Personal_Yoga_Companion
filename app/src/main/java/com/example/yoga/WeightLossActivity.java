// MorningRoutineActivity.java
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

public class WeightLossActivity extends AppCompatActivity {

    private RecyclerView posesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_routine);

        // Header Setup
        ImageView backBtn = findViewById(R.id.btn_back);
        backBtn.setOnClickListener(v -> finish());

        TextView title = findViewById(R.id.tv_header_title);
        title.setText("Weight Loss Activity");

        // Poses List Setup
        posesRecyclerView = findViewById(R.id.rv_poses);
        posesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<YogaPose> poses = new ArrayList<>();
        poses.add(new YogaPose("Boat Pose", "3 mins", R.drawable.boat_pose, "Strengthens core muscles"));
        poses.add(new YogaPose("Plank Pose", "2 mins", R.drawable.prank_pose, "Builds core strength"));
        poses.add(new YogaPose("Warrior II", "3 mins", R.drawable.warrior_two, "Tones thighs and shoulders"));
        poses.add(new YogaPose("Chair Pose", "2 mins", R.drawable.chair_pose, "Burns calories and strengthens lower body"));
        poses.add(new YogaPose("Bridge Pose", "3 mins", R.drawable.bridge_pose, "Tones abdominal muscles"));


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

            // Add click listener for the Try Now button
            holder.btnTryIt.setOnClickListener(v -> {
                if (pose.name.equals("Boat Pose")) {
                    Intent intent = new Intent(WeightLossActivity.this, BoatPoseActivity.class);
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
            MaterialButton btnTryIt; // Add this line

            ViewHolder(View itemView) {
                super(itemView);
                ivPose = itemView.findViewById(R.id.iv_pose);
                tvName = itemView.findViewById(R.id.tv_pose_name);
                tvDuration = itemView.findViewById(R.id.tv_duration);
                tvInstructions = itemView.findViewById(R.id.tv_instructions);
                btnTryIt = itemView.findViewById(R.id.btn_try_it); // Add this line
            }
        }
    }
}