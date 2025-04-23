package com.example.yoga;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {YogaSession.class}, version = 1)
public abstract class YogaDatabase extends RoomDatabase {
    private static YogaDatabase instance;

    public abstract YogaSessionDao yogaSessionDao();

    public static synchronized YogaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            YogaDatabase.class, "yoga_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // For simplicity; not recommended in production
                    .build();
        }
        return instance;
    }
}
