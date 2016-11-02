package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tenhrs.courses.R;
import com.tenhrs.courses.databinding.ActivityCoursesListBinding;
import com.tenhrs.courses.model.Course;

import java.util.List;


public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView list;
    private GridLayoutManager lLayout;
    private List<Course> coursesList=null;
    private ActivityCoursesListBinding activityCoursesListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCoursesListBinding = DataBindingUtil.setContentView(this, R.layout.activity_courses_list);
        activityCoursesListBinding.imgOffice.setOnClickListener(this);
        activityCoursesListBinding.imgAndroid.setOnClickListener(this);
        activityCoursesListBinding.imgAngulerjs.setOnClickListener(this);
        activityCoursesListBinding.imgBigdata.setOnClickListener(this);
        activityCoursesListBinding.imgHtml.setOnClickListener(this);
        activityCoursesListBinding.imgIos.setOnClickListener(this);
        activityCoursesListBinding.imgPmp.setOnClickListener(this);



//        lLayout = new GridLayoutManager(this, 2);
//
//        list = (RecyclerView)findViewById(R.id.grid_all_langs);
//        list.setHasFixedSize(true);
//        list.setLayoutManager(lLayout);
//        CourseDB courseDB=new CourseDB(this);
//        coursesList=courseDB.getCourses(0);
//        LanguagesAdapter languagesAdapter = new LanguagesAdapter(this,coursesList);
//        list.setAdapter(languagesAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_office:
                launchWeeklyActivity();
            break;
            case R.id.img_html:
                launchWeeklyActivity();
                break;
            case R.id.img_bigdata:
                launchWeeklyActivity();
                break;
            case R.id.img_ios:
                launchWeeklyActivity();
                break;
            case R.id.img_angulerjs:
                launchWeeklyActivity();
                break;
            case R.id.img_android:
                launchWeeklyActivity();
                break;
            case R.id.img_pmp:
                launchWeeklyActivity();

                break;
        }

    }
    private void launchWeeklyActivity(){
        startActivity(new Intent(this,WeeklyCourseActivity.class));
    }
}
