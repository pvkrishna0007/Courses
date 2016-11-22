package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tenhrs.courses.R;
import com.tenhrs.courses.adapter.LanguagesAdapter;
import com.tenhrs.courses.database.CourseDB;
import com.tenhrs.courses.databinding.ActivityCoursesListBinding;
import com.tenhrs.courses.databinding.ActivityHomeScreanBinding;
import com.tenhrs.courses.model.Course;

import java.util.List;


public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView list;
    private GridLayoutManager lLayout;
    private List<Course> coursesList=null;
    private ActivityCoursesListBinding activityCoursesListBinding;
    private ActivityHomeScreanBinding homeScreanBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         homeScreanBinding=DataBindingUtil.setContentView(this, R.layout.activity_home_screan);
        lLayout = new GridLayoutManager(this, 2);
        list = (RecyclerView)homeScreanBinding.rvAllCourses;
        list.setHasFixedSize(true);
        list.setLayoutManager(lLayout);
        CourseDB courseDB=new CourseDB(this);
        coursesList=courseDB.getCourses(0);
        LanguagesAdapter languagesAdapter = new LanguagesAdapter(this,coursesList);
        list.setAdapter(languagesAdapter);

    }


    @Override
    public void onClick(View v) {
//

    }
    private void launchWeeklyActivity(){
        startActivity(new Intent(this,SelectedCoursesActivity.class));
    }
}
