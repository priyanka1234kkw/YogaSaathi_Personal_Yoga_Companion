package com.example.yoga;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class PoseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose_detail);

        ImageView poseImage = findViewById(R.id.iv_pose);
        TextView poseName = findViewById(R.id.tv_pose_name);
        TextView poseDuration = findViewById(R.id.tv_duration);
        MaterialButton btnStart = findViewById(R.id.btn_start);

        // Get data from intent
        String name = getIntent().getStringExtra("pose_name");
        String duration = getIntent().getStringExtra("duration");
        String instructions = getIntent().getStringExtra("instructions");

        // Set data to views
        poseName.setText(name);
        poseDuration.setText(duration);
        poseImage.setImageResource(getIntent().getIntExtra("image_res", R.drawable.img_1));

        btnStart.setOnClickListener(v -> {
            // Implement your pose timer/flow here
        });
    }
}