package com.example.yoga;

import android.content.Intent; // ✅ Intent
import android.os.Bundle;
import android.view.View; // ✅ View
import android.widget.Button; // ✅ Button

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.adapter.PoseAdapter;

import java.util.ArrayList;
import java.util.Calendar; // ✅ Calendar
import java.util.List;

public class YogaDayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Pose> poseList;
    private PoseAdapter adapter;
    private Button viewReportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoga_day_activity);

        recyclerView = findViewById(R.id.recyclerView);
        viewReportBtn = findViewById(R.id.viewReportButton);

        poseList = getTodayPoses();
        adapter = new PoseAdapter(poseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int completed = 0;
                for (Pose pose : poseList) {
                    if (pose.isTried()) completed++;
                }

                Intent intent = new Intent(YogaDayActivity.this, ReportActivity.class);
                intent.putExtra("completed", completed);
                intent.putExtra("total", poseList.size());
                startActivity(intent);
            }
        });
    }

    private List<Pose> getTodayPoses() {
        List<Pose> poses = new ArrayList<>();
        int day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) % 7;

        switch (day) {
            case 0:
                poses.add(new Pose("Mountain Pose", "30 sec", "Stand tall and breathe deeply.", "Improves posture"));
                poses.add(new Pose("Child’s Pose", "45 sec", "Relax and stretch the back.", "Relieves stress"));
                poses.add(new Pose("Cobra Pose", "30 sec", "Strengthens the spine.", "Improves flexibility"));
                break;
            case 1:
                poses.add(new Pose("Downward Dog", "45 sec", "Stretch your back and legs.", "Boosts circulation"));
                poses.add(new Pose("Warrior II", "30 sec", "Strengthens legs.", "Improves focus"));
                poses.add(new Pose("Bridge Pose", "40 sec", "Opens chest and spine.", "Reduces anxiety"));
                break;
            default:
                poses.add(new Pose("Tree Pose", "30 sec", "Balance on one foot.", "Improves stability"));
                poses.add(new Pose("Cat-Cow", "45 sec", "Alternate back stretches.", "Increases spine mobility"));
                poses.add(new Pose("Seated Twist", "40 sec", "Twist your torso gently.", "Aids digestion"));
                break;
        }
        return poses;
    }

}
