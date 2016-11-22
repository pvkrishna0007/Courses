package com.tenhrs.courses.model;

/**
 * Created by SIVA on 15-11-2016.
 */

public class FlashCard {
    private int questionID;
    private String  choiceName;
    private String  question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }


}
