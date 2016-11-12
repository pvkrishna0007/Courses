package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.tenhrs.courses.R;
import com.tenhrs.courses.databinding.ActivityWeeklyCoursesNewBinding;

/**
 * Created by Krishna on 10/25/2016.
 */
public class WeeklyCourseActivity extends BaseCompactActivity implements View.OnClickListener {

    private ActivityWeeklyCoursesNewBinding activityCoursesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityCoursesBinding = DataBindingUtil.setContentView(this, R.layout.activity_weekly_courses_new);
        activityCoursesBinding.cbFlashcards.setOnClickListener(this);
//        init();   //vvvvvbbb
    }

    private void init() {

    }


    private void launchOptionsActivity(){
        startActivity(new Intent(this,OptionsActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_flashcards:
                launchOptionsActivity();
                break;
        }
    }
}
