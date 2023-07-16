package com.example.mobileapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobileapp.Entities.Course;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);
    @Update
    void update(Course course);
    @Delete
    void delete(Course course);

    @Query("SELECT * FROM COURSES ORDER BY courseID ASC")
    List<Course> getAllCourses();

    @Query("SELECT * FROM COURSES WHERE termID= :termID ORDER BY courseID ASC")
    List<Course> getAllCoursesForTerm(int termID);
}
