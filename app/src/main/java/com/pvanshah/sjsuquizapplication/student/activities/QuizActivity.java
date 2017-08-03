package com.pvanshah.sjsuquizapplication.student.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eftimoff.viewpagertransformers.ForegroundToBackgroundTransformer;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.pvanshah.sjsuquizapplication.R;
import com.pvanshah.sjsuquizapplication.student.adapters.QuizPagerAdapter;
import com.pvanshah.sjsuquizapplication.student.model.Question;
import com.pvanshah.sjsuquizapplication.student.model.Response;
import com.pvanshah.sjsuquizapplication.student.util.Numerics;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by avinash on 7/17/17.
 */

public class QuizActivity extends AppCompatActivity {

    public static final String ID = "id";
    static final String TITLE = "title";

    @Bind(R.id.pager_quiz)
    ViewPager quizPager;

    @Bind(R.id.viewpager_indicator)
    InkPageIndicator pagerIndicator;

    public List<Response> responseList = new ArrayList();

    @OnClick(R.id.prev_btn)
    void moveBack(){
        if(quizPager!=null && quizPager.getCurrentItem() > 0){
            quizPager.setCurrentItem(quizPager.getCurrentItem()-1,true);
        }
    }

    @OnClick(R.id.next_btn)
    void moveForward(){
        if(quizPager!=null && quizPager.getCurrentItem() < quizPager.getAdapter().getCount()){
            quizPager.setCurrentItem(quizPager.getCurrentItem()+1,true);
        }
    }

    @OnClick(R.id.submit)
    void submit() {
        if(quizPager!=null && responseList != null) {
            if(quizPager.getAdapter().getCount() > 0 && responseList.size() == quizPager.getAdapter().getCount()) {
                MaterialDialog dialog = new MaterialDialog.Builder(QuizActivity.this)
                        .title(R.string.sure)
                        .content(R.string.submit_ask)
                        .positiveText(R.string.submit)
                        .negativeText(R.string.no)
                        .contentColor(Color.GRAY)
                        .backgroundColorRes(android.R.color.white)
                        .positiveColorRes(R.color.colorAccent)
                        .negativeColorRes(R.color.colorAccent)
                        .autoDismiss(false)
                        .onPositive(new MaterialDialog.SingleButtonCallback(){

                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                callToSaveResponses(responseList);
                                dialog.dismiss();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            } else {
                MaterialDialog dialog = new MaterialDialog.Builder(QuizActivity.this)
                        .title(R.string.sure)
                        .content(R.string.attempt_all)
                        .positiveText(R.string.submit)
                        .negativeText(R.string.no)
                        .contentColor(Color.GRAY)
                        .backgroundColorRes(android.R.color.white)
                        .positiveColorRes(R.color.colorAccent)
                        .negativeColorRes(R.color.colorAccent)
                        .autoDismiss(false)
                        .onPositive(new MaterialDialog.SingleButtonCallback(){

                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                callToSaveResponses(responseList);
                                dialog.dismiss();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }
    }

    private void callToSaveResponses(List<Response> responseList) {
        String response="";
        int total = 0;
        for(Response response1 : responseList) {
            response = response+response1.getActualAnswer()+"-"+response1.getAttemptedAnswer()+"\n";
            if(response1.getAttemptedAnswer().equals(response1.getActualAnswer())) {
                total = total + 1;
            }
        }
        Toast.makeText(QuizActivity.this, response+"\n Total = "+total, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quizPager.setOffscreenPageLimit(Numerics.FIFTY);

        Intent intent = getIntent();
        if(intent != null) {
            startQuiz(intent);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    private void startQuiz(Intent intent) {
        String id = intent.getStringExtra(ID);
        String title = intent.getStringExtra(TITLE);
        if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(title)){
            getQuizQuestions();
            getSupportActionBar().setTitle(title);
        } else {
            finish();
        }
    }

    private void getQuizQuestions() {
        //TODO: get quiz questions based on ID

        setQuestionsToPager(getDummyQuestions());
    }

    private void setQuestionsToPager(List<Question> questions) {
        final QuizPagerAdapter quizPagerAdapter= new QuizPagerAdapter(getSupportFragmentManager(), questions);
        quizPager.setAdapter(quizPagerAdapter);
        pagerIndicator.setViewPager(quizPager);
        quizPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
        quizPager.setClipToPadding(false);
        quizPager.setPadding(Numerics.TEN,0,Numerics.TEN,0);
        quizPager.setPageMargin(Numerics.TEN);
    }

    private List<Question> getDummyQuestions() {
        List<Question> questions = new ArrayList();

        Question question = new Question();
        question.setQuizId("aasdqgnoalsupamdoqnoiq123ro9inoaw");
        question.setQuestionNumber("1");
        question.setQuestion("Who is the Wife of Chandler?");
        question.setOptions("Rachel");
        question.setOptions("Monica");
        question.setOptions("Ross");
        question.setOptions("Phoebe");
        question.setAnswer("2");
        questions.add(question);

        question = new Question();
        question.setQuizId("aasdqgnoalsupamdoqnoiq123ro9inoaw");
        question.setQuestionNumber("2");
        question.setQuestion("Who is the Wife of Ross?");
        question.setOptions("Chandler");
        question.setOptions("Monica");
        question.setOptions("Rachel");
        question.setOptions("Joey");
        question.setAnswer("3");
        questions.add(question);

        question = new Question();
        question.setQuizId("aasdqgnoalsupamdoqnoiq123ro9inoaw");
        question.setQuestionNumber("3");
        question.setQuestion("Who is the Wife of Joey?");
        question.setOptions("Monica");
        question.setOptions("Ross");
        question.setOptions("Phoebe");
        question.setOptions("None");
        question.setAnswer("4");
        questions.add(question);

        question = new Question();
        question.setQuizId("aasdqgnoalsupamdoqnoiq123ro9inoaw");
        question.setQuestionNumber("4");
        question.setQuestion("Who is the Sister of Ross?");
        question.setOptions("Rachel");
        question.setOptions("Monica");
        question.setOptions("Chanlder");
        question.setOptions("Phoebe");
        question.setAnswer("2");
        questions.add(question);

        question = new Question();
        question.setQuizId("aasdqgnoalsupamdoqnoiq123ro9inoaw");
        question.setQuestionNumber("5");
        question.setQuestion("What is the name of Frank Jr.'s Daughter?");
        question.setOptions("Rachel");
        question.setOptions("Monica");
        question.setOptions("Phoebe");
        question.setOptions("Chanlder");
        question.setAnswer("4");
        questions.add(question);

        question = new Question();
        question.setQuizId("aasdqgnoalsupamdoqnoiq123ro9inoaw");
        question.setQuestionNumber("6");
        question.setQuestion("Who did not work at a coffee house / restaurant?");
        question.setOptions("Rachel");
        question.setOptions("Monica");
        question.setOptions("Joey");
        question.setOptions("Chandler");
        question.setAnswer("4");
        questions.add(question);

        return questions;
    }

    @Override
    public void onBackPressed() {
        showWarningDialog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showWarningDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showWarningDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(QuizActivity.this)
                .title(R.string.sure)
                .content(R.string.quit_quiz)
                .positiveText(R.string.quit)
                .negativeText(R.string.no)
                .contentColor(Color.GRAY)
                .backgroundColorRes(android.R.color.white)
                .positiveColorRes(R.color.colorAccent)
                .negativeColorRes(R.color.colorAccent)
                .autoDismiss(false)
                .onPositive(new MaterialDialog.SingleButtonCallback(){

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
