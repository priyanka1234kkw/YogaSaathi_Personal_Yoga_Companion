package com.example.yoga;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class YogaReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String time = intent.getStringExtra("time");

        if ("morning".equals(time)) {
            NotificationHelper.showNotification(
                    context, "Good Morning 🌅", "Start your day with yoga and mindfulness!");
        } else if ("evening".equals(time)) {
            NotificationHelper.showNotification(
                    context, "Evening Stretch 🌙", "Unwind and relax with a calming yoga session.");
        }
    }
}
