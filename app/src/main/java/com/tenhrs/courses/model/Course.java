package com.tenhrs.courses.model;

/**
 * Created by SIVA on 06-10-2016.
 */
public class Course {
    private int courseID;
    private String courseName;
    private int isActive;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }


}
