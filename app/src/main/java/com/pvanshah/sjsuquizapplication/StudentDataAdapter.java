package com.pvanshah.sjsuquizapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.pvanshah.sjsuquizapplication.firebaseutils.FirebaseConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Pavan Shah on 8/1/2017.
 */

public class StudentDataAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;
    ArrayList<StudentDetails> resultList = new ArrayList<>();
    Context context;

    public StudentDataAdapter(Context context) {
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public StudentDataAdapter(Context context, ArrayList<StudentDetails> arrayList) {
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
        TextView studentName;
        LinearLayout studentRow;
    }

    public void datasetchanged(ArrayList<StudentDetails> arrayList) {
        resultList = arrayList;
        Log.d("student", "Data set changed");
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        StudentDataAdapter.Holder holder = new StudentDataAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.student_list, null);
        final StudentDetails studentDetails = resultList.get(position);

        holder.studentName = rowView.findViewById(R.id.studentName);
        holder.studentName.setText(studentDetails.getUsername());

        holder.studentRow = rowView.findViewById(R.id.studentRow);
        holder.studentRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,StudentProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        return rowView;
    }
}
