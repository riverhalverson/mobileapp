package com.example.mobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Entities.Course;
import com.example.mobileapp.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseItemView;

        private CourseViewHolder(View itemview){
            super(itemview);
            courseItemView = itemview.findViewById(R.id.TextViewCourseTitle);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);

                    Intent intent = new Intent(context, CourseDetails.class);

                    intent.putExtra("courseid", current.getCourseID());
                    intent.putExtra("termid", current.getTermID());
                    intent.putExtra("coursetitle", current.getCourseTitle());
                    intent.putExtra("startdate", current.getStartDate());
                    intent.putExtra("enddate", current.getEndDate());
                    intent.putExtra("status", current.getStatus());
                    intent.putExtra("instructorname", current.getInstructorName());
                    intent.putExtra("instructorphone", current.getInstructorPhone());
                    intent.putExtra("instructoremail", current.getInstructorEmail());
                    intent.putExtra("note", current.getNote());

                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);

        return new CourseAdapter.CourseViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if(mCourses != null){
            Course current = mCourses.get(position);
            String title = current.getCourseTitle();

            holder.courseItemView.setText(title);
        }
        else{
            holder.courseItemView.setText("No Course Name");
        }
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void setCourses(List<Course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

}
