package com.pvanshah.sjsuquizapplication.student.model;

import com.google.android.gms.common.api.Response;

/**
 * Created by avinash on 7/17/17.
 */

public class ResponseObject {

    private String total;

    private Response[] response;

    private String username;

    private String quizID;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Response[] getResponse() {
        return response;
    }

    public void setResponse(Response[] response) {
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }
}
