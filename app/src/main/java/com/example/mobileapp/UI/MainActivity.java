package com.example.mobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapp.Database.Repository;
import com.example.mobileapp.Entities.Assessment;
import com.example.mobileapp.Entities.Course;
import com.example.mobileapp.Entities.Term;
import com.example.mobileapp.R;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //sample data
        //Repository repository = new Repository(getApplication());
        //repository.insert(assessment);

        Button termButton = findViewById(R.id.termsbutton);
        termButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TermsList.class);
                startActivity(intent);
            }
        });

        Button courseButton = findViewById(R.id.coursesbutton);
        courseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CoursesList.class);
                startActivity(intent);
            }
        });

        Button assessmentsButton = findViewById(R.id.assessmentsbutton);
        assessmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AssessmentsList.class);
                startActivity(intent);
            }
        });



    }


}