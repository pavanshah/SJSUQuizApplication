package com.pvanshah.sjsuquizapplication.student.model;

/**
 * Created by avinash on 7/17/17.
 */

public class Response {

    private String quizId;
    private String questionNumber;
    private String actualAnswer;
    private String attemptedAnswer;


    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getActualAnswer() {
        return actualAnswer;
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer;
    }

    public String getAttemptedAnswer() {
        return attemptedAnswer;
    }

    public void setAttemptedAnswer(String attemptedAnswer) {
        this.attemptedAnswer = attemptedAnswer;
    }
}
