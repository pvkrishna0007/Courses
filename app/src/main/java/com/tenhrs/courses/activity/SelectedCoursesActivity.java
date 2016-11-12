package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tenhrs.courses.R;
import com.tenhrs.courses.databinding.ActivitySelectedCoursesListBinding;

public class SelectedCoursesActivity extends AppCompatActivity implements View.OnClickListener
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         ActivitySelectedCoursesListBinding coursesListBinding= DataBindingUtil.setContentView(this, R.layout.activity_selected_courses_list);
        coursesListBinding.imgAddcourseSelected.setOnClickListener(this);
        coursesListBinding.imgAndroidSelected.setOnClickListener(this);
        coursesListBinding.imgAngulerjsSelected.setOnClickListener(this);
        coursesListBinding.imgBigdataSelected.setOnClickListener(this);
        coursesListBinding.imgHtmlSelected.setOnClickListener(this);
        coursesListBinding.imgIosSelected.setOnClickListener(this);
        coursesListBinding.imgOfficeSelected.setOnClickListener(this);
        coursesListBinding.imgPmpSelected.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_office_selected:
                launchWeeklyActivity();
                break;
            case R.id.img_html_selected:
                launchWeeklyActivity();
                break;
            case R.id.img_bigdata_selected:
                launchWeeklyActivity();
                break;
            case R.id.img_ios_selected:
                launchWeeklyActivity();
                break;
            case R.id.img_angulerjs_selected:
                launchWeeklyActivity();
                break;
            case R.id.img_android_selected:
                launchWeeklyActivity();
                break;
            case R.id.img_pmp_selected:
                launchWeeklyActivity();

                break;
            case R.id.img_addcourse_selected:
                startActivity(new Intent(this,HomeScreen.class));

                break;
        }
    }
    private void launchWeeklyActivity(){
        startActivity(new Intent(this,WeeklyCourseActivity.class));
    }
}
