package com.pvanshah.sjsuquizapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.pvanshah.sjsuquizapplication.firebaseutils.FirebaseConfiguration;

import java.util.ArrayList;

public class ProfessorHomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FloatingActionButton addQuiz;
    private ListView quizlist;
    private ListView studentlist;
    QuizDataAdapter quizDataAdapter;
    StudentDataAdapter studentDataAdapter;
    TextView tabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);

        //All declarations
        final ArrayList<StudentDetails> studentDetails = new ArrayList<StudentDetails>();
        studentlist=(ListView) findViewById(R.id.studentlist);
        studentlist.setVisibility(View.VISIBLE);
        studentDataAdapter = new StudentDataAdapter(getApplicationContext(), studentDetails);
        studentlist.setAdapter(studentDataAdapter);

        final ArrayList<QuizDetails> quizData = new ArrayList<QuizDetails>();
        quizlist=(ListView) findViewById(R.id.quizList);
        quizlist.setVisibility(View.INVISIBLE);
        quizDataAdapter = new QuizDataAdapter(getApplicationContext(), quizData);
        quizlist.setAdapter(quizDataAdapter);

        tabTitle = (TextView) findViewById(R.id.tabTitle);
        addQuiz = (FloatingActionButton) findViewById(R.id.addQuiz);
        addQuiz.setVisibility(View.INVISIBLE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Retrive all the data from firebase here
        final DatabaseReference studentReference = FirebaseConfiguration.getStudentData();
        studentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("student", "data received "+dataSnapshot);
                studentDetails.clear();

                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    StudentDetails data = child.getValue(StudentDetails.class);
                    StudentDetails childData = new StudentDetails();
                    childData.setUsername(data.getUsername());
                    studentDetails.add(childData);
                }

                studentDataAdapter.datasetchanged(studentDetails);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        final DatabaseReference quizReference = FirebaseConfiguration.getQuizData();
        quizReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //QuizDetails quizData = dataSnapshot.getValue(QuizDetails.class)
                quizData.clear();

                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    QuizDetails data = child.getValue(QuizDetails.class);
                    QuizDetails childData = new QuizDetails();
                    childData.setQuizID(data.getQuizID());
                    childData.setQuizTitle(data.getQuizTitle());
                    //QuizDetails childData = child.getValue(QuizDetails.class);
                    Log.d("Quiz", "data "+childData.getQuizTitle());
                    quizData.add(childData);
                }

                quizDataAdapter.datasetchanged(quizData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        addQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNewQuiz();

            }
        });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    addQuiz.setVisibility(View.INVISIBLE);
                    quizlist.setVisibility(View.INVISIBLE);
                    studentlist.setVisibility(View.VISIBLE);
                    tabTitle.setText("My Class");
                    return true;
                case R.id.navigation_dashboard:
                    addQuiz.setVisibility(View.INVISIBLE);
                    quizlist.setVisibility(View.INVISIBLE);
                    studentlist.setVisibility(View.INVISIBLE);
                    tabTitle.setText("My Analytics");
                    return true;
                case R.id.navigation_notifications:
                    addQuiz.setVisibility(View.VISIBLE);
                    quizlist.setVisibility(View.VISIBLE);
                    studentlist.setVisibility(View.INVISIBLE);
                    tabTitle.setText("My Quizzes");
                    return true;
            }
            return false;
        }

    };


    public void addNewQuiz()
    {
        // custom dialog
        final Dialog dialog = new Dialog(ProfessorHomeActivity.this);
        dialog.setContentView(R.layout.customtoast);
        dialog.setTitle("Title...");

        //get the spinner from the xml.
        final Spinner dropdown = (Spinner) dialog.findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        Integer[] items = new Integer[]{1,2, 3, 4, 5, 6, 7 , 8 , 9 ,10};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        final EditText quizTitle = (EditText) dialog.findViewById(R.id.quizName);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = quizTitle.getText().toString();
                int noOfQuestions = (int) dropdown.getSelectedItem();

                Intent intent = new Intent(ProfessorHomeActivity.this, CreateQuiz.class);
                intent.putExtra("quizTitle", title);
                intent.putExtra("noOfQuestions", noOfQuestions);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
