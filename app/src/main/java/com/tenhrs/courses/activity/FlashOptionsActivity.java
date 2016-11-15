package com.tenhrs.courses.activity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;


import com.tenhrs.courses.R;
import com.tenhrs.courses.databinding.FragmentFlashCardsOptionsBinding;

/**
 * Created by Krishna on 11/11/2016.
 */

public class FlashOptionsActivity extends BaseCompactActivity implements View.OnClickListener {

    private FragmentFlashCardsOptionsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_flash_cards_options);

        init();
    }

    private void init() {

        binding.viewFlipper.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.view_flipper:
                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.flip_animation);
                anim.setTarget(binding.rlFirst);
                anim.setDuration(3000);
                anim.start();
//                binding.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_left));
//                binding.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.out_from_right));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.viewFlipper.showNext();
                    }
                }, 1500);

        }
    }
}
