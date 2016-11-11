package com.tenhrs.courses.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.tenhrs.courses.BaseCompactActivity;
import com.tenhrs.courses.R;
import com.tenhrs.courses.databinding.FragmentOptionsBinding;

/**
 * Created by Krishna on 11/11/2016.
 */

public class OptionsActivity extends BaseCompactActivity implements View.OnClickListener {

    private FragmentOptionsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_options);

        init();
    }

    private void init() {
        binding.ivOptionOne.setOnClickListener(this);
        binding.ivOptionTwo.setOnClickListener(this);
        binding.ivOptionThree.setOnClickListener(this);
        binding.ivOptionFour.setOnClickListener(this);
    }

    private void setOption(int position) {
        binding.btnNext.setEnabled(true);

        setOptionSelection(position, binding.ivOptionOne, binding.ivOptionTwo, binding.ivOptionThree, binding.ivOptionFour);
    }

    private void setOptionSelection(int position, ImageView... views) {
        for (int i = 0; i < views.length; i++) {
            if (position == i) {
                views[i].getBackground().setLevel(1);
            } else {
                views[i].getBackground().setLevel(2);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivOptionOne:
                setOption(0);
                break;

            case R.id.ivOptionTwo:
                setOption(1);
                break;

            case R.id.ivOptionThree:
                setOption(2);
                break;

            case R.id.ivOptionFour:
                setOption(3);
                break;
        }
    }
}
