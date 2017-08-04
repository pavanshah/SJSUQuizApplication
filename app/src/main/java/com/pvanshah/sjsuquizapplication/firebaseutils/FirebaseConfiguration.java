package com.pvanshah.sjsuquizapplication.firebaseutils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by avinash on 7/02/2017.
 */

public class FirebaseConfiguration {

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseRoot;
    private static DatabaseReference quizData;
    private static DatabaseReference questionsData;
    private static DatabaseReference applicationUsers;
    private static DatabaseReference resultRef;
    private static FirebaseAuth mAuth;

    public void configureFirebase(){
        //Firebase configuration with all elements
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRoot = firebaseDatabase.getReference();
        quizData = databaseRoot.child("Quizzes");
        questionsData = databaseRoot.child("Questions");
        applicationUsers = databaseRoot.child("ApplicationUsers");
        resultRef = databaseRoot.child("result");
        mAuth = FirebaseAuth.getInstance();
    }

    public static DatabaseReference getApplicationUsers() {
        return applicationUsers;
    }

    public static FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public static DatabaseReference getDatabaseRoot() {
        return databaseRoot;
    }

    public static DatabaseReference getQuizData() {
        return quizData;
    }

    public static DatabaseReference getQuestionsData() {
        return questionsData;
    }

    public static FirebaseAuth getmAuth() {return mAuth;}

    public static DatabaseReference getResultRef() {
        return resultRef;
    }
}
