package com.pvanshah.sjsuquizapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Pavan Shah on 8/1/2017.
 */

public class QuizDataAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;
    ArrayList<QuizDetails> resultList = new ArrayList<>();
    Context context;

    public QuizDataAdapter(Context context) {
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public QuizDataAdapter(Context context,  ArrayList<QuizDetails> arrayList) {
        this.context=context;
        resultList=arrayList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView quizTitle;
        TextView quizID;
        Button publish;
    }

    public void datasetchanged(ArrayList<QuizDetails> arrayList) {
        resultList = arrayList;
        Log.d("Quiz", "Data set changed");
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        QuizDataAdapter.Holder holder = new QuizDataAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.quiz_list, null);
        QuizDetails quizDetails = resultList.get(position);

        holder.quizTitle =(TextView) rowView.findViewById(R.id.quizTitle);
        Log.d("Quiz", "title "+quizDetails.getQuizTitle());
        holder.quizTitle.setText(quizDetails.getQuizTitle());

        holder.quizID =(TextView) rowView.findViewById(R.id.quizID);
        holder.quizID.setText(quizDetails.getQuizID());
        Log.d("Quiz", "id "+quizDetails.getQuizID());

        holder.publish = (Button) rowView.findViewById(R.id.publishQuiz);

        holder.publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Quiz Published", Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }
}
