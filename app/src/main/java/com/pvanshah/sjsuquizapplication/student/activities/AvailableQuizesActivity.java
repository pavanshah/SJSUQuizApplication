package com.pvanshah.sjsuquizapplication.student.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pvanshah.sjsuquizapplication.R;
import com.pvanshah.sjsuquizapplication.student.adapters.QuizListAdapter;
import com.pvanshah.sjsuquizapplication.student.base.AbstractBaseAdapter;
import com.pvanshah.sjsuquizapplication.student.base.BaseAppCompatActivity;
import com.pvanshah.sjsuquizapplication.student.model.Quiz;
import com.pvanshah.sjsuquizapplication.student.network.NetworkStateChangeListener;
import com.pvanshah.sjsuquizapplication.student.network.NetworkStateListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by avinash on 7/17/17.
 */

public class AvailableQuizesActivity extends BaseAppCompatActivity implements NetworkStateChangeListener{

    private boolean isDataFetched;

    @Bind(R.id.lst_quiz)
    ListView quizzes;
    private AbstractBaseAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_quiz));
        NetworkStateListener.registerNetworkState(this);
        callQuizzesService();
    }

    private void callQuizzesService() {
        //TODO: get data for list of quizzes


        setDataToList(getDummyQuizzes());
    }

    private List getDummyQuizzes() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Fall Quiz");
        quiz.setId("aasdqgnoalsupamdoqnoiq123ro9inoaw");
        List<Quiz> quizList = new ArrayList();
        quizList.add(quiz);
        quiz = new Quiz();
        quiz.setTitle("Fall Mid");
        quiz.setId("lajjshnoalsupajdkqnoiq987ro9inpoi");
        quizList.add(quiz);
        return quizList;
    }

    private void setDataToList(final List quizList) {
        isDataFetched = true;
        adapter = new QuizListAdapter(this).addItems(quizList);
        quizzes.setAdapter(adapter);
        quizzes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startQuiz(((Quiz)quizList.get(position)).getId(), ((Quiz) quizList.get(position)).getTitle());
            }
        });
    }

    private void startQuiz(String id, String title) {
        Intent intent = new Intent(AvailableQuizesActivity.this, QuizActivity.class);
        intent.putExtra(QuizActivity.ID, id);
        intent.putExtra(QuizActivity.TITLE, title);
        startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_available_quizes;
    }

    @Override
    public void onNetworkStateChanged(int networkState) {
        if (networkState == NetworkStateListener.NETWORK_CONNECTED) {
            if (!isDataFetched) {
                callQuizzesService();
            }
        } else if (networkState == NetworkStateListener.NO_NETWORK_CONNECTIVITY) {
            // disconnected
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkStateListener.unregisterNetworkState(this);
    }

    @Override
    protected void onPause() {
        NetworkStateListener.unregisterNetworkState(this);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.account:
                startActivity(new Intent(AvailableQuizesActivity.this, AccountActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
