package com.pvanshah.sjsuquizapplication;

/**
 * Created by Pavan Shah on 7/31/2017.
 */

public class QuizDetails {

    private String quizID;
    private String quizTitle;

    public QuizDetails(){

    }

    public QuizDetails(String quizID, String quizTitle) {
        this.quizID = quizID;
        this.quizTitle = quizTitle;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
}
