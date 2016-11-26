package com.tenhrs.courses.database;

/**
 * Created by Krishna on 11/18/2016.
 */

public interface DbTable {

    String TABLE_COURSE = "Courses";
    String TABLE_CATEGORY = "Categories";
    String TABLE_QUESTION = "Questions";
    String TABLE_WEEKS = "Weeks";
    String TABLE_ANSWER = "UserQuesAnswer";
    String TABLE_CHOICE = "QuestionChoice";

    String CHOICE_ID = "ChiceID";
    String CHOICE_NAME = "ChoiceName";
    String CHOICE_QUESTION_ID = "QuestionID";
    String CHOICE_IS_RIGHT_CHOICE = "IsRightChoice";

    String ANSWER_ID = "SelectedOptionId";
    String ANSWER_QUESTION_ID = "QuestionID";
    String ANSWER_COURSE_ID = "ChoiceID";
    String ANSWER_IS_RIGHT = "IsRight";
    String ANSWER_ANSWER_TIME = "AnswerTime";

    String COURSE_ID = "CourseID";
    String COURSE_NAME = "CourseName";
    String COURSE_IS_ACTIVE = "isActive";

    String CATEGORY_ID = "CategoryId";
    String CATEGORY_NAME = "CategoryName";

    String QUESTION_ID = "QuestionId";
    String QUESTION_NAME = "QuestionName";
    String QUESTION_WEEK_ID = "WeekID";
    String QUESTION_COURSE_ID = "CourseID";
    String QUESTION_IS_ACTIVE = "isActive";

    String WEEK_ID = "weekID";
    String WEEK_NAME = "WeekName";

}

class QuestionModel {
    private String id = "QuestionId";
    private String name = "QuestionName";
    private String weekID = "WeekID";

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private String courseID = "CourseID";
    private boolean isActive;

}