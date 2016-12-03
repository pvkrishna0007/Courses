package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tenhrs.courses.R;
import com.tenhrs.courses.common.Constants;
import com.tenhrs.courses.database.DbTable;
import com.tenhrs.courses.databinding.ActivityWeeklyCoursesNewBinding;

/**
 * Created by Krishna on 10/25/2016.
 */
public class WeeklyCourseActivity extends BaseCompactActivity implements View.OnClickListener {

    private ActivityWeeklyCoursesNewBinding activityCoursesBinding;
    private int courseID;
    private int weekID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityCoursesBinding = DataBindingUtil.setContentView(this, R.layout.activity_weekly_courses_new);
        activityCoursesBinding.cbFlashcards.setOnClickListener(this);
        activityCoursesBinding.cbQuiz.setOnClickListener(this);
        activityCoursesBinding.cbWeek1.setOnClickListener(this);
        activityCoursesBinding.cbWeek2.setOnClickListener(this);
        activityCoursesBinding.cbWeek3.setOnClickListener(this);
        activityCoursesBinding.cbWeek4.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            courseID=bundle.getInt(Constants.COURSEID);
        }
//        init();   //vvvvvbbb
    }

    private void init() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_week1:
                weekID=1;
                activityCoursesBinding.cbWeek1.setChecked(true);
                activityCoursesBinding.cbWeek2.setChecked(false);
                activityCoursesBinding.cbWeek3.setChecked(false);
                activityCoursesBinding.cbWeek4.setChecked(false);
                break;
            case R.id.cb_week2:
                weekID=2;
                activityCoursesBinding.cbWeek1.setChecked(false);
                activityCoursesBinding.cbWeek2.setChecked(true);
                activityCoursesBinding.cbWeek3.setChecked(false);
                activityCoursesBinding.cbWeek4.setChecked(false);
                break;
            case R.id.cb_week3:
                weekID=3;
                activityCoursesBinding.cbWeek1.setChecked(false);
                activityCoursesBinding.cbWeek2.setChecked(false);
                activityCoursesBinding.cbWeek3.setChecked(true);
                activityCoursesBinding.cbWeek4.setChecked(false);
                break;
            case R.id.cb_week4:
                weekID=4;
                activityCoursesBinding.cbWeek1.setChecked(false);
                activityCoursesBinding.cbWeek2.setChecked(false);
                activityCoursesBinding.cbWeek3.setChecked(false);
                activityCoursesBinding.cbWeek4.setChecked(true);
                break;
            case R.id.cb_flashcards:
                if(weekID==0){
                    Toast.makeText(this,"Please select the week. ",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent=new Intent(this,FlashOptionsActivity.class);
                Bundle extras = new Bundle();
                extras.putInt(DbTable.WEEK_ID, weekID);
                extras.putInt(DbTable.COURSE_ID, courseID);
                intent.putExtras(extras);
               startActivity(intent);
                break;
            case R.id.cb_quiz:
                if(weekID==0){
                    Toast.makeText(this,"Please select the week. ",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent qintent = new Intent(this, OptionsActivity.class);
                Bundle qextras = new Bundle();
                qextras.putInt(DbTable.WEEK_ID, weekID);
                qextras.putInt(DbTable.COURSE_ID, courseID);
//                extras.putInt(OptionsActivity.QUESTION_COUNT, 20);
                qintent.putExtras(qextras);
                startActivity(qintent);
                break;
        }
    }
}
