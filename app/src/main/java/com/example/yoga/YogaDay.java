package com.example.yoga;


import java.util.List;

public class YogaDay {
    private String dayTitle;
    private List<Pose> poses;

    public YogaDay(String dayTitle, List<Pose> poses) {
        this.dayTitle = dayTitle;
        this.poses = poses;
    }

    public String getDayTitle() { return dayTitle; }
    public List<Pose> getPoses() { return poses; }
}
