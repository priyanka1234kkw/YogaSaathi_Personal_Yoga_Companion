package com.example.yoga;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReportActivity extends AppCompatActivity {

    private YogaDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        TextView tvReport = findViewById(R.id.tvReport);

        int completed = getIntent().getIntExtra("completed", 0);
        int total = getIntent().getIntExtra("total", 0);

        String reportText = "You completed " + completed + " out of " + total + " poses today!\n";
        reportText += (completed == total) ? "🏆 Great job!" : "💪 Keep going!";
        tvReport.setText(reportText);

        // ✅ Store into Database
        db = YogaDatabase.getInstance(this);

        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        // ✅ Create and insert the YogaSession
        YogaSession session = new YogaSession(today, completed);
        db.yogaSessionDao().insert(session);
    }
}
