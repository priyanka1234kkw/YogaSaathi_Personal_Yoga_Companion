package com.example.yoga;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private YogaHistoryAdapter adapter;
    private List<YogaSession> sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        YogaDatabase db = YogaDatabase.getInstance(this);
        sessions = db.yogaSessionDao().getAllSessions();

        adapter = new YogaHistoryAdapter(sessions);
        recyclerView.setAdapter(adapter);
    }
}
