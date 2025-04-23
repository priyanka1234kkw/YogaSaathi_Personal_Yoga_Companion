package com.example.yoga;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BoatPoseActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton, stopButton, musicButton;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ImageView animationView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30000;
    private MediaPlayer mediaPlayer;
    private ActivityAdapter activityAdapter;
    private ArrayList<ActivityModel> activityList;
    private AnimationDrawable suryaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boat_pose_activity);

        // Initialize UI components
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        musicButton = findViewById(R.id.musicButton);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        animationView = findViewById(R.id.animationView);

        // Setup animation
        animationView.setBackgroundResource(R.drawable.surya_animation);
        suryaAnimation = (AnimationDrawable) animationView.getBackground();

        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityList = new ArrayList<>();
        activityAdapter = new ActivityAdapter(activityList);
        recyclerView.setAdapter(activityAdapter);

        // Button click listeners
        startButton.setOnClickListener(v -> startTimer());
        stopButton.setOnClickListener(v -> stopTimer());
        musicButton.setOnClickListener(v -> toggleMusic());
    }

    private void startTimer() {
        suryaAnimation.start();
        progressBar.setMax((int) (timeLeftInMillis / 1000));

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerDisplay();
                progressBar.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00:00");
                suryaAnimation.stop();
                saveActivityRecord();
            }
        }.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timerTextView.setText("00:30");
        progressBar.setProgress(30);
        timeLeftInMillis = 30000;
        suryaAnimation.stop();
    }

    private void toggleMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.yoga_music);
            mediaPlayer.start();
            musicButton.setText("Stop Music");
        } else {
            mediaPlayer.release();
            mediaPlayer = null;
            musicButton.setText("Play Music");
        }
    }

    private void updateTimerDisplay() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerTextView.setText(String.format(Locale.getDefault(), "00:%02d", seconds));
    }

    private void saveActivityRecord() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        activityList.add(0, new ActivityModel(timestamp, "Completed 30-second session"));
        activityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    // RecyclerView Adapter
    private static class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

        private final ArrayList<ActivityModel> activities;

        ActivityAdapter(ArrayList<ActivityModel> activities) {
            this.activities = activities;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_log, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ActivityModel activity = activities.get(position);
            holder.timestamp.setText(activity.getTimestamp());
            holder.description.setText(activity.getDescription());
        }

        @Override
        public int getItemCount() {
            return activities.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView timestamp, description;

            ViewHolder(View itemView) {
                super(itemView);
                timestamp = itemView.findViewById(R.id.tvTimestamp);
                description = itemView.findViewById(R.id.tvDescription);
            }
        }
    }

    // Data Model
    private static class ActivityModel {
        private final String timestamp;
        private final String description;

        ActivityModel(String timestamp, String description) {
            this.timestamp = timestamp;
            this.description = description;
        }

        String getTimestamp() { return timestamp; }
        String getDescription() { return description; }
    }
}
