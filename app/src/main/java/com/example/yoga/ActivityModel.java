package com.example.yoga;


public class ActivityModel {
    private String dateTime;
    private String activityDetail;

    public ActivityModel(String dateTime, String activityDetail) {
        this.dateTime = dateTime;
        this.activityDetail = activityDetail;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getActivityDetail() {
        return activityDetail;
    }
}
