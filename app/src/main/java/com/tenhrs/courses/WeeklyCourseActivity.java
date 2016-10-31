package com.tenhrs.courses;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tenhrs.courses.databinding.ActivityWeeklyCoursesNewBinding;

/**
 * Created by Krishna on 10/25/2016.
 */
public class WeeklyCourseActivity extends BaseCompactActivity {

    private ActivityWeeklyCoursesNewBinding activityCoursesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCoursesBinding = DataBindingUtil.setContentView(this, R.layout.activity_weekly_courses_new);

        init();
    }

    private void init() {

    }
}