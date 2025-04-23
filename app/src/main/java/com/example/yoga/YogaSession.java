package com.example.yoga;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class YogaSession {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private int completed;

    public YogaSession(String date, int completed) {
        this.date = date;
        this.completed = completed;
    }

    // Required empty constructor for Room
    public YogaSession() {}

    public int getId() { return id; }
    public String getDate() { return date; }
    public int getCompleted() { return completed; }

    public void setId(int id) { this.id = id; }
    public void setDate(String date) { this.date = date; }
    public void setCompleted(int completed) { this.completed = completed; }
}
