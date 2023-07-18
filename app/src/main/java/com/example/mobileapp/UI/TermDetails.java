package com.example.mobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobileapp.Database.Repository;
import com.example.mobileapp.Entities.Course;
import com.example.mobileapp.Entities.Term;
import com.example.mobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class TermDetails extends AppCompatActivity {

    EditText editTermTitle;
    EditText editTermStart;
    EditText editTermEnd;
    String title;
    String startDate;
    String endDate;
    int termid;

    Term term;
    Repository  repository;

    List<Course> filteredCourses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        //populate term fields
        editTermTitle = findViewById(R.id.termname);
        editTermStart = findViewById(R.id.termstartdate);
        editTermEnd = findViewById(R.id.termenddate);

        termid = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        startDate = getIntent().getStringExtra("startdate");
        endDate = getIntent().getStringExtra("enddate");

        editTermTitle.setText(title);
        editTermStart.setText(startDate);
        editTermEnd.setText(endDate);

        repository = new Repository(getApplication());

        //populate associated courses
        RecyclerView recyclerView = findViewById(R.id.courserecyclerview);

        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //sort for associated courses only
        for(Course course : repository.getAllCourses()){
            if(course.getTermID() == termid){
                filteredCourses.add(course);
            }
        }
        courseAdapter.setCourses(filteredCourses);

        //saves term information entered
        Button saveButton = findViewById(R.id.termsavebutton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(termid == -1){
                    term = new Term(0, editTermTitle.getText().toString(),
                            editTermStart.getText().toString(),
                            editTermEnd.getText().toString());
                    repository.insert(term);
                }
                else{
                    term = new Term(termid, editTermTitle.getText().toString(),
                            editTermStart.getText().toString(),
                            editTermEnd.getText().toString());
                    repository.update(term);
                }
            }
        });

        Button newCourse = findViewById(R.id.newcoursebutton);

        newCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetails.this, CourseDetails.class);
                intent.putExtra("termid", termid);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        for(Course course : repository.getAllCourses()){
            if(course.getTermID() == termid){
                filteredCourses.add(course);
            }
        }
        RecyclerView recyclerView = findViewById(R.id.courserecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);

        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(filteredCourses);
    }
}