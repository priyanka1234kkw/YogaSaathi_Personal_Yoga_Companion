package com.example.yoga;

public class Pose {
    private String name;
    private String duration;
    private String description;
    private String benefit;
    private boolean isTried;

    public Pose(String name, String duration, String description, String benefit) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.benefit = benefit;
        this.isTried = false;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getDuration() { return duration; }
    public String getDescription() { return description; }
    public String getBenefit() { return benefit; }
    public boolean isTried() { return isTried; }
    public void setTried(boolean tried) { isTried = tried; }
}
