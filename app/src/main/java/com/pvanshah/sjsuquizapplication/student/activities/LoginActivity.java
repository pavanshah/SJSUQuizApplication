package com.pvanshah.sjsuquizapplication.student.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pvanshah.sjsuquizapplication.ProfessorHomeActivity;
import com.pvanshah.sjsuquizapplication.R;
import com.pvanshah.sjsuquizapplication.student.base.BaseAppCompatActivity;
import com.pvanshah.sjsuquizapplication.student.util.Preferences;

import java.util.Arrays;

/**
 * Created by avinash on 7/14/17.
 */

public class LoginActivity extends BaseAppCompatActivity {

    private static final int RC_SIGN_IN = 1123;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String username = "";
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.login_activity);
        mFirebaseAuth = FirebaseAuth.getInstance();
        if (mFirebaseAuth.getCurrentUser() != null) {
            if(mFirebaseAuth.getCurrentUser().getEmail().equalsIgnoreCase("pavanrajendrakumar.shah@sjsu.edu"))
            {
                startActivity(new Intent(LoginActivity.this, ProfessorHomeActivity.class));
                LoginActivity.this.finish();
            }
            else
            {
                startActivity(new Intent(LoginActivity.this, AvailableQuizesActivity.class));
                LoginActivity.this.finish();
            }
        } else {
            AppCompatButton gBtn = (AppCompatButton) findViewById(R.id.google_btn);
            gBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                    startActivityForResult(AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN
                    );
                }
            });
            mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        if(user.getEmail().equalsIgnoreCase("pavanrajendrakumar.shah@sjsu.edu")){
                            Toast.makeText(LoginActivity.this, "Welcome Professor", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, ProfessorHomeActivity.class));
                            LoginActivity.this.finish();
                        }
                        else if (user.getEmail().contains("@sjsu.edu")) {
                            username = user.getDisplayName();
                            email = user.getEmail();
                            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email)) {
                                Preferences.getIns().storeString(Preferences.USER_NAME, username);
                                Preferences.getIns().storeString(Preferences.USER_EMAIL, email);
                                dismissDialog();
                                startActivity(new Intent(LoginActivity.this, AvailableQuizesActivity.class));
                                LoginActivity.this.finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Please Sign In using SJSU email ID", Toast.LENGTH_LONG).show();
                            AuthUI.getInstance().signOut(LoginActivity.this);
                        }
                    }
                }
            };
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(LoginActivity.this, "Signed In!", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(LoginActivity.this, "Sign In Cancelled!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
