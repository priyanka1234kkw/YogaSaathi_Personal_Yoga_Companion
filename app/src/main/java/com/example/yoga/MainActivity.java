package com.example.yoga;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.pm.PackageManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Schedule daily yoga reminder notifications
        scheduleYogaReminders();

        // Card click listeners with animation
        setupCard(R.id.yoga_practice, yoga_plan.class);
        setupCard(R.id.chatbot_assistance, ChatActivity.class);
        setupCard(R.id.track_progress, YogaDayActivity.class);
        setupCard(R.id.attend_class, ExploreActivity.class);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

    }

    private void setupCard(int cardId, final Class<?> targetActivity) {
        CardView card = findViewById(cardId);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(
                        MainActivity.this,
                        R.anim.scale_up
                ));
                startActivity(new Intent(MainActivity.this, targetActivity));
            }
        });
    }

    private void scheduleYogaReminders() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Morning Reminder at 7:00 AM
        scheduleAlarm("morning", 7, 0, alarmManager);

        // Evening Reminder at 7:00 PM
        scheduleAlarm("evening", 19, 0, alarmManager);
    }

    private void scheduleAlarm(String tag, int hour, int minute, AlarmManager alarmManager) {
        Intent intent = new Intent(this, YogaReminderReceiver.class);
        intent.putExtra("time", tag);  // You can use this in the receiver to show custom messages

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                tag.hashCode(),  // Unique request code
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // If time already passed today, schedule for tomorrow
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );
    }
}
