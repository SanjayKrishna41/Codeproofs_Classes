package com.example.codeproofs.codeproofsclasses;

public class CourseDetails {
    private String courseName;
    private int thumbnail;

    public CourseDetails(String courseName, int thumbnail) {
        this.courseName = courseName;
        this.thumbnail = thumbnail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
