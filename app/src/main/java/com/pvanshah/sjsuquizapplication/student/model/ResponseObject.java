package com.pvanshah.sjsuquizapplication.student.model;

import java.util.List;

/**
 * Created by avinash on 7/17/17.
 */

public class ResponseObject {

    private String total;

    private List<Response> response;

    private String email;

    private String name;

    private String quizID;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
