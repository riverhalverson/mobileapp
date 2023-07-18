package com.example.mobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobileapp.Database.Repository;
import com.example.mobileapp.Entities.Assessment;
import com.example.mobileapp.Entities.Course;
import com.example.mobileapp.Entities.Term;
import com.example.mobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class AssessmentDetails extends AppCompatActivity {

    EditText editAssessmentTitle;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;
    EditText editAssessmentType;
    String assessmentTitle;
    String assessmentStart;
    String assessmentEnd;
    String assessmentType;
    int assessmentid;
    int courseid;

    Assessment assessment;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        //populate fields
        editAssessmentTitle = findViewById(R.id.assessmentname);
        editAssessmentStart = findViewById(R.id.assessmentstartdate);
        editAssessmentEnd = findViewById(R.id.assessmentenddate);
        editAssessmentType = findViewById(R.id.assessmenttype);

        assessmentid = getIntent().getIntExtra("assessmentid", -1);
        courseid = getIntent().getIntExtra("courseid", -1);
        assessmentTitle = getIntent().getStringExtra("title");
        assessmentStart = getIntent().getStringExtra("startdate");
        assessmentEnd = getIntent().getStringExtra("enddate");
        assessmentType = getIntent().getStringExtra("testtype");

        editAssessmentTitle.setText(assessmentTitle);
        editAssessmentStart.setText(assessmentStart);
        editAssessmentEnd.setText(assessmentEnd);
        editAssessmentType.setText(assessmentType);

        repository = new Repository(getApplication());

        Button saveButton = findViewById(R.id.assessmentsavebutton);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(assessmentid == -1) {
                    Assessment assessment = new Assessment(0, courseid,
                            editAssessmentTitle.getText().toString(),
                            editAssessmentStart.getText().toString(),
                            editAssessmentEnd.getText().toString(),
                            editAssessmentType.getText().toString());

                    repository.insert(assessment);
                }
                else{
                    Assessment assessment = new Assessment(assessmentid, courseid,
                            editAssessmentTitle.getText().toString(),
                            editAssessmentStart.getText().toString(),
                            editAssessmentEnd.getText().toString(),
                            editAssessmentType.getText().toString());

                    repository.update(assessment);
                }
            }
        });

    }
}