package com.pvanshah.sjsuquizapplication.student.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.pvanshah.sjsuquizapplication.R;
import com.pvanshah.sjsuquizapplication.student.base.BaseAppCompatActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by avinash on 7/19/17.
 */

public class AccountActivity extends BaseAppCompatActivity {

    @Bind(R.id.name)
    TextView nameTxt;

    @Bind(R.id.email)
    TextView emailTxt;

    @OnClick(R.id.sign_out)
    void signOut() {
        AuthUI.getInstance().signOut(AccountActivity.this);
        Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        AccountActivity.this.finish();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_account;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        nameTxt.setText(firebaseAuth.getCurrentUser().getDisplayName());
        emailTxt.setText(firebaseAuth.getCurrentUser().getEmail());
        getSupportActionBar().setTitle(R.string.account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
