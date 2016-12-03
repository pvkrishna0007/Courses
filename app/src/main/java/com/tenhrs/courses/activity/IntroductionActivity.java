package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.tenhrs.courses.R;
import com.tenhrs.courses.adapter.IntroductionPageAdapter;
import com.tenhrs.courses.databinding.ActivityIntroductionBinding;

/**
 * Created by SIVA on 22-11-2016.
 */

public class IntroductionActivity extends BaseCompactActivity implements View.OnClickListener {
    Handler handler = new Handler();
    private ActivityIntroductionBinding viewDataBinding;
    private IntroductionPageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_introduction);
        viewDataBinding.btnSignup.setOnClickListener(this);
        adapter = new IntroductionPageAdapter(this);
        viewDataBinding.viewpager.setAdapter(adapter);
        viewDataBinding.tabDots.setupWithViewPager(viewDataBinding.viewpager);
        handler.post(sendData);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(sendData);
    }

    private final Runnable sendData = new Runnable() {
        public void run() {
            viewDataBinding.viewpager.setCurrentItem((viewDataBinding.viewpager.getCurrentItem() + 1) % adapter.getCount());

            handler.postDelayed(this, 2000);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:
                startActivity(new Intent(this,SelectedCoursesActivity.class));
            break;
        }
    }
}
