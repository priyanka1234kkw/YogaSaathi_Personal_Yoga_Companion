// AdvancedPoseActivity.java
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

public class AdvancedPosesActivity extends AppCompatActivity {

    private RecyclerView posesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_pose);

        // Header Setup
        ImageView backBtn = findViewById(R.id.btn_back);
        backBtn.setOnClickListener(v -> finish());

        TextView title = findViewById(R.id.tv_header_title);
        title.setText("Advanced Poses");

        // Poses List Setup
        posesRecyclerView = findViewById(R.id.rv_poses);
        posesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<YogaPose> poses = new ArrayList<>();
        poses.add(new YogaPose("Headstand", "5 mins", R.drawable.headstand, "Strengthens shoulders and core"));
        poses.add(new YogaPose("Crow Pose", "3 mins", R.drawable.crow, "Improves arm balance"));
        poses.add(new YogaPose("Wheel Pose", "4 mins", R.drawable.wheel_pose, "Deep backbend stretch"));
        poses.add(new YogaPose("Peacock Pose", "3 mins", R.drawable.peopock, "Improves digestion and balance"));
        poses.add(new YogaPose("Firefly Pose", "4 mins", R.drawable.firefly, "Enhances flexibility and strength"));

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
                if (pose.name.equals("Headstand")) {
                    Intent intent = new Intent(AdvancedPosesActivity.this, HeadStandActivity.class);
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
