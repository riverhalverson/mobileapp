package com.example.mobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mobileapp.Database.Repository;
import com.example.mobileapp.Entities.Assessment;
import com.example.mobileapp.Entities.Course;
import com.example.mobileapp.Entities.Term;
import com.example.mobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class CourseDetails extends AppCompatActivity {

    EditText editCourseName;
    EditText editCourseStartDate;
    EditText editCourseEndDate;
    EditText editCourseStatus;
    EditText editInstructorName;
    EditText editInstructorEmail;
    EditText editInstructorPhone;
    EditText editCourseNotes;


    String courseName;
    String courseStartDate;
    String courseEndDate;
    String instructorName;
    String instructorEmail;
    String instructorPhone;
    String courseStatus;
    String courseNotes;

    int courseid;
    int termid;

    Course course;

    Repository repository;

    List<Assessment> filteredAssessments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        //populate course fields
        editCourseName = findViewById(R.id.coursename);
        editCourseStartDate = findViewById(R.id.coursestartdate);
        editCourseEndDate = findViewById(R.id.courseenddate);
        editCourseStatus = findViewById(R.id.coursestatus);
        editInstructorName = findViewById(R.id.instructorname);
        editInstructorEmail = findViewById(R.id.instructoremail);
        editInstructorPhone = findViewById(R.id.instructorsphone);
        editCourseNotes = findViewById(R.id.coursenotes);

        courseid = getIntent().getIntExtra("courseid", -1);
        termid = getIntent().getIntExtra("termid", -1);
        courseName = getIntent().getStringExtra("coursetitle");
        courseStartDate = getIntent().getStringExtra("startdate");
        courseEndDate = getIntent().getStringExtra("enddate"); //these must match adapter
        courseStatus = getIntent().getStringExtra("status");
        instructorName = getIntent().getStringExtra("instructorname");
        instructorEmail = getIntent().getStringExtra("instructoremail");
        instructorPhone = getIntent().getStringExtra("instructorphone");
        courseNotes = getIntent().getStringExtra("note");

        editCourseName.setText(courseName);
        editCourseStartDate.setText(courseStartDate);
        editCourseEndDate.setText(courseEndDate);
        editCourseStatus.setText(courseStatus);
        editInstructorName.setText(instructorName);
        editInstructorEmail.setText(instructorEmail);
        editInstructorPhone.setText(instructorPhone);
        editCourseNotes.setText(courseNotes);

        repository = new Repository(getApplication());

        //populate associated assessments for this course

        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);

        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //sort for associated assessments only
        for(Assessment assessment : repository.getAllAssessments()){
            if(assessment.getCourseID() == courseid){
                filteredAssessments.add(assessment);
            }
        }

        assessmentAdapter.setAssessments(filteredAssessments);

        //saves info

        Button saveButton = findViewById(R.id.coursesavebutton);

        saveButton.setOnClickListener(new View.OnClickListener(){
            //need to add spinner input still
            @Override
            public void onClick(View view){
                if(courseid == -1) {
                    Course course = new Course(0,
                            termid,
                            editCourseName.getText().toString(),
                            editCourseStartDate.getText().toString(),
                            editCourseEndDate.getText().toString(),
                            editCourseStatus.getText().toString(),
                            editInstructorName.getText().toString(),
                            editInstructorPhone.getText().toString(),
                            editInstructorEmail.getText().toString(),
                            editCourseNotes.getText().toString());

                    repository.insert(course);
                }
                else{
                    Course course = new Course(courseid,
                            termid,
                            editCourseName.getText().toString(),
                            editCourseStartDate.getText().toString(),
                            editCourseEndDate.getText().toString(),
                            editCourseStatus.getText().toString(),
                            editInstructorName.getText().toString(),
                            editInstructorPhone.getText().toString(),
                            editInstructorEmail.getText().toString(),
                            editCourseNotes.getText().toString());

                    repository.update(course);
                }
            }
        });

        Button newAssessment = findViewById(R.id.addassessmentbutton);

        newAssessment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CourseDetails.this, AssessmentDetails.class);
                intent.putExtra("courseid", courseid);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        for(Assessment assessment : repository.getAllAssessments()){
            if(assessment.getCourseID() == courseid){
                filteredAssessments.add(assessment);
            }
        }
        RecyclerView recyclerView = findViewById(R.id.assessmentrecyclerview);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);

        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(filteredAssessments);
    }
}