package com.pvanshah.sjsuquizapplication.student.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avinash on 7/17/17.
 */

public class Question implements Serializable{
    private String quizId;
    private String questionNumber;
    private String question;
    private List<String> options = new ArrayList();
    private String answer;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(String option){
        options.add(option);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
}
