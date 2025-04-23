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

public class MorningRoutineActivity extends AppCompatActivity {

    private RecyclerView posesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_routine);

        // Header Setup
        ImageView backBtn = findViewById(R.id.btn_back);
        backBtn.setOnClickListener(v -> finish());

        TextView title = findViewById(R.id.tv_header_title);
        title.setText("Morning Routine");

        // Poses List Setup
        posesRecyclerView = findViewById(R.id.rv_poses);
        posesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<YogaPose> poses = new ArrayList<>();
        poses.add(new YogaPose("Sun Salutation", "5 mins", R.drawable.sun_salute, "Start your day with energy"));
        poses.add(new YogaPose("Mountain Pose", "2 mins", R.drawable.mouintain, "Grounding posture"));
        poses.add(new YogaPose("Downward Dog", "3 mins", R.drawable.downward_dog, "Full body stretch"));
        poses.add(new YogaPose("Warrior I", "2 mins", R.drawable.warrior, "Build strength"));
        poses.add(new YogaPose("Child's Pose", "3 mins", R.drawable.childspose, "Relaxation"));

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
                if (pose.name.equals("Sun Salutation")) {
                    Intent intent = new Intent(MorningRoutineActivity.this, SuryaNamaskarActivity.class);
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