package com.example.yoga;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class ProgramsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        TextView textView = findViewById(R.id.tvPrograms);
        textView.setText("Yoga Programs");
    }
}
