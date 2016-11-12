package com.tenhrs.courses.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tenhrs.courses.BaseCompactActivity;
import com.tenhrs.courses.R;
import com.tenhrs.courses.databinding.ActivityResultStatusBinding;

/**
 * Created by Krishna on 11/12/2016.
 */

public class ResultStatusActivity extends BaseCompactActivity {


    private ActivityResultStatusBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result_status);
    }
}
