package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;

import com.tenhrs.courses.R;
import com.tenhrs.courses.databinding.ActivityLoginBinding;


public class LoginActivity extends BaseCompactActivity {

    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        startActivity(new Intent(this,SelectedCoursesActivity.class));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


}
