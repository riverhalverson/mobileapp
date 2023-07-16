package com.example.mobileapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mobileapp.DAO.AssessmentDAO;
import com.example.mobileapp.DAO.CourseDAO;
import com.example.mobileapp.DAO.TermDAO;
import com.example.mobileapp.Entities.Assessment;
import com.example.mobileapp.Entities.Course;
import com.example.mobileapp.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version=1, exportSchema = false)
public abstract class ScheduleDatabaseBuilder extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract TermDAO termDAO();

    private static volatile ScheduleDatabaseBuilder INSTANCE;

    static ScheduleDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ScheduleDatabaseBuilder.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScheduleDatabaseBuilder.class,
                            "ScheduleDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
