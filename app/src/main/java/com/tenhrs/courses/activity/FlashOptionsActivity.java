package com.tenhrs.courses.activity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.tenhrs.courses.R;
import com.tenhrs.courses.common.OnSwipeTouchListener;
import com.tenhrs.courses.database.FlashCardsDB;
import com.tenhrs.courses.databinding.FragmentFlashCardsOptionsBinding;
import com.tenhrs.courses.model.FlashCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 11/11/2016.
 */

public class FlashOptionsActivity extends BaseCompactActivity implements View.OnClickListener {

    private FragmentFlashCardsOptionsBinding binding;
    private int curentQuesID=0;
    List<FlashCard> cardsList;
    private boolean isValisList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_flash_cards_options);
        cardsList=new ArrayList<FlashCard>();
        FlashCardsDB flashCardsDB=new FlashCardsDB(this);
        cardsList=flashCardsDB.getFlashCards(1,1);
        if(cardsList!=null&&cardsList.size()>0){
            isValisList=true;
            curentQuesID=0;
            bindData(curentQuesID);
        }
        init();
    }

    private void init() {

        binding.viewFlipper.setOnClickListener(this);
        binding.ivFlashBg.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Toast.makeText(FlashOptionsActivity.this, "top", Toast.LENGTH_SHORT).show();
                moveNext();
                bindData(curentQuesID);
            }
            public void onSwipeRight() {
                moveNext();
                bindData(curentQuesID);
            }
            public void onSwipeLeft() {
                Toast.makeText(FlashOptionsActivity.this, "left", Toast.LENGTH_SHORT).show();
                movePrevious();
                bindData(curentQuesID);
            }
            public void onSwipeBottom() {
                Toast.makeText(FlashOptionsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                movePrevious();
                bindData(curentQuesID);
            }

        });



    }
    private void bindData(int curentQuesID){
       if(isValisList) {
           FlashCard flashCard = cardsList.get(curentQuesID);
           binding.tvQuestion.setText(flashCard.getQuestion());
           binding.tvChoice.setText(flashCard.getChoiceName());
           updateProgressBar();
       }

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
    private void moveNext(){
        if(curentQuesID==cardsList.size()-1){
            curentQuesID=0;
        }else{
            curentQuesID=curentQuesID+1;
        }
    }
    private void movePrevious(){
        if(curentQuesID==0){
            curentQuesID=cardsList.size()-1;
        }else{
            curentQuesID=curentQuesID-1;
        }
    }
    public void updateProgressBar(){
        try {
            float progress=(float)(curentQuesID+1)/cardsList.size();
            progress=progress*100;
            binding.pbStatus.setProgress(Math.round(progress));
            binding.tvScore.setText(curentQuesID+1 + "/" + cardsList.size());
        }catch(Exception e){

        }
    }

}
