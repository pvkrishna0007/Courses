package com.tenhrs.courses.model;

import java.util.ArrayList;

/**
 * Created by SIVA on 22-11-2016.
 */

public class QuestionOptions {

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    private ArrayList<Option> options = new ArrayList<>();

    public static class Option {
        @Override
        public String toString() {
            return "Option{" +
                    "optionName='" + optionName + '\'' +
                    ", isRightAnswer='" + isRightAnswer + '\'' +
                    ", optionId=" + optionId +
                    ", questionId=" + questionId +
                    '}';
        }

        private String optionName;
        private boolean isRightAnswer;
        private int optionId;
        private int questionId;

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }

        public boolean getIsRightAnswer() {
            return isRightAnswer;
        }

        public void setIsRightAnswer(boolean isRightAnswer) {
            this.isRightAnswer = isRightAnswer;
        }

        public int getOptionId() {
            return optionId;
        }

        public void setOptionId(int optionId) {
            this.optionId = optionId;
        }

    }
}
