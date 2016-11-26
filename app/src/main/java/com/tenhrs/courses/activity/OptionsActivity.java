package com.tenhrs.courses.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenhrs.courses.R;
import com.tenhrs.courses.database.CourseDB;
import com.tenhrs.courses.database.DbTable;
import com.tenhrs.courses.databinding.FragmentOptionsBinding;
import com.tenhrs.courses.model.Question;
import com.tenhrs.courses.model.QuestionOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 11/11/2016.
 */

public class OptionsActivity extends BaseCompactActivity implements View.OnClickListener {

    private FragmentOptionsBinding binding;
    private int totalNumberQuestion;
    private int currentQuestionNumber;
    private int correctAnswerCount;
    //    public static final String QUESTION_COUNT="QuestionCount";
    private static final int LEVEL_CORRECT_ANSWER = 1, LEVEL_WRONG_ANSWER = 2, LEVEL_DEFAULT_OPTION = 0;
    private List<Question> quizQuestions;
    private CourseDB courseDB;
    private int selectedOption = -1;
    private ArrayList<QuestionOptions.Option> options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_options);

        init();
    }

    private void init() {
        courseDB = new CourseDB(this);

        binding.ivOptionOne.setOnClickListener(this);
        binding.ivOptionTwo.setOnClickListener(this);
        binding.ivOptionThree.setOnClickListener(this);
        binding.ivOptionFour.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int weekId = extras.getInt(DbTable.WEEK_ID);
            int courseId = extras.getInt(DbTable.COURSE_ID);
            quizQuestions = courseDB.getQuizQuestions(weekId, courseId);
//            totalNumberQuestion = extras.getInt(QUESTION_COUNT);
            totalNumberQuestion = quizQuestions.size();
        }
    }

    private void setOption(int position) {
        binding.btnNext.setEnabled(true);
        selectedOption = position;

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

    private void changeToNextQuestion() {

        if (currentQuestionNumber == totalNumberQuestion) {
            binding.btnNext.setVisibility(View.GONE);
            binding.btnSubmit.setVisibility(View.VISIBLE);
        }

        selectedOption = -1;
        Question question = quizQuestions.get(currentQuestionNumber);
        binding.tvQuestion.setText(question.getName());

        options = courseDB.getOptions(question.getId());
        binding.tvOptionOne.setText(options.get(0).getOptionName());
        binding.tvOptionTwo.setText(options.get(1).getOptionName());
        binding.tvOptionThree.setText(options.get(2).getOptionName());
        binding.tvOptionFour.setText(options.get(3).getOptionName());

        binding.tvOptionOne.setTag(options.get(0));
        binding.tvOptionTwo.setTag(options.get(1));
        binding.tvOptionThree.setTag(options.get(2));
        binding.tvOptionFour.setTag(options.get(3));

        currentQuestionNumber++;

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
            case R.id.btn_next:
                Log.d("QuestionLogs", "TotalQuestions: " + totalNumberQuestion + "  currentQuestion: " + currentQuestionNumber);

                binding.btnNext.setEnabled(false);
                showAnswer();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeToNextQuestion();
                        binding.btnNext.setEnabled(true);
                    }
                }, 2000);
                break;
            case R.id.btn_submit:

                Log.d("AnswerLogs", "TotalQuestions: " + totalNumberQuestion + "  correctAnswers: " + correctAnswerCount);
                binding.btnNext.setEnabled(false);
                showAnswer();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.btnNext.setEnabled(true);
                        startActivity(new Intent(getApplicationContext(), ResultStatusActivity.class));
                    }
                }, 2000);
                break;
        }
    }

    private void showAnswer() {
        validateIndividualOption(binding.tvOptionOne, binding.ivOptionOne);
        validateIndividualOption(binding.tvOptionTwo, binding.ivOptionTwo);
        validateIndividualOption(binding.tvOptionThree, binding.ivOptionThree);
        validateIndividualOption(binding.tvOptionFour, binding.ivOptionFour);

        showErrorIfExist();

        if (selectedOption > 0) {
            QuestionOptions.Option option = options.get(selectedOption);
            if (option.getIsRightAnswer()) {
                correctAnswerCount++;
            }
        }

    }

    private void validateIndividualOption(TextView tvOption, ImageView ivOption) {
        QuestionOptions.Option option = (QuestionOptions.Option) tvOption.getTag();
        ivOption.getDrawable().setLevel(option.getIsRightAnswer() ? LEVEL_CORRECT_ANSWER : LEVEL_DEFAULT_OPTION);
    }

    private void showErrorIfExist() {
        switch (selectedOption) {
            case 0:
                validatErrorOption(binding.tvOptionOne, binding.ivOptionOne);
                break;
            case 1:
                validatErrorOption(binding.tvOptionTwo, binding.ivOptionTwo);
                break;
            case 2:
                validatErrorOption(binding.tvOptionThree, binding.ivOptionThree);
                break;
            case 4:
                validatErrorOption(binding.tvOptionFour, binding.ivOptionFour);
                break;
        }

    }

    private void validatErrorOption(TextView tvOption, ImageView ivOption) {
        QuestionOptions.Option option = (QuestionOptions.Option) tvOption.getTag();
        ivOption.getDrawable().setLevel(option.getIsRightAnswer() ? LEVEL_CORRECT_ANSWER : LEVEL_WRONG_ANSWER);
    }
}
