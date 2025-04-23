package com.example.yoga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class yoga_plan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoga_plan);

        // Back Arrow to go back to the previous activity
        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes current activity and returns to the previous one
            }
        });

        /* "Let's Started" Button
        Button btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigates to PoseLibraryActivity when "Let's Started" is clicked
                Intent intent = new Intent(yoga_plan.this, PoseLibraryActivity.class);
                startActivity(intent);
            }
        });*/

        // Find included layouts and set unique content for each card
        setYogaPlanContent(findViewById(R.id.Morning_Routine), "Morning Routine", "Beginner-friendly", R.drawable.morning);
        setYogaPlanContent(findViewById(R.id.Evening_Relax), "Evening Relax", "Intermediate Level", R.drawable.evening);
        setYogaPlanContent(findViewById(R.id.Advanced_Poses), "Advanced Poses", "Advanced Level", R.drawable.advanced);
        setYogaPlanContent(findViewById(R.id.Back_Pain), "Back Pain", "Expert Level", R.drawable.back_pain);
        setYogaPlanContent(findViewById(R.id.Stress_Relief), "Stress Relief", "Expert Level", R.drawable.stress_relief);
        setYogaPlanContent(findViewById(R.id.Weight_Loss), "Weight Loss", "Expert Level", R.drawable.weight_loss);
    }

    private void setYogaPlanContent(View cardView, String title, String description, int imageRes) {
        if (cardView != null) {
            TextView tvTitle = cardView.findViewById(R.id.tv_plan_title);
            TextView tvDesc = cardView.findViewById(R.id.tv_plan_desc);
            ImageView ivImage = cardView.findViewById(R.id.iv_plan_image);

            tvTitle.setText(title);
            tvDesc.setText(description);
            ivImage.setImageResource(imageRes);

            // Add click listener to the card
            cardView.setOnClickListener(v -> {
                if(title.equals("Morning Routine")) {
                    startActivity(new Intent(yoga_plan.this, MorningRoutineActivity.class));
                }
                // Add other category handlers here
                else if(title.equals("Evening Relax")) {
                    startActivity(new Intent(yoga_plan.this, EveningRelaxActivity.class));
                }
                else if(title.equals("Advanced Poses")) {
                    startActivity(new Intent(yoga_plan.this, AdvancedPosesActivity.class));
                }
                else if(title.equals("Weight Loss")) {
                    startActivity(new Intent(yoga_plan.this, WeightLossActivity.class));
                }

            });
        }
    }
}
