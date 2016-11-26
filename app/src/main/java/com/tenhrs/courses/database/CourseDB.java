package com.tenhrs.courses.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tenhrs.courses.model.Course;
import com.tenhrs.courses.model.Question;
import com.tenhrs.courses.model.QuestionOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIVA on 06-10-2016.
 */
public class CourseDB implements DbTable {
    private Context context;
    private DatabaseHelper databaseHelper;

    public CourseDB(Context context){
        this.context=context;
    }

    public List<Course> getCourses(int isActive){
        databaseHelper = DatabaseHelper.getInstance();
        List<Course> courseList = new ArrayList<>();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("select * from " + TABLE_COURSE);
        if(isActive==1){
            stringBuilder.append(" where " + COURSE_IS_ACTIVE + " = " + isActive);
        }
        String qry=stringBuilder.toString();
        SQLiteDatabase database = databaseHelper.openDatabase();
        Cursor cursor = database.rawQuery(qry,null);
        if(cursor!=null&&cursor.moveToFirst()){
            do{
                Course course=new Course();
                course.setCourseID(cursor.getInt(cursor.getColumnIndex(COURSE_ID)));
                course.setCourseName(cursor.getString(cursor.getColumnIndex(COURSE_NAME)));
                course.setIsActive(cursor.getInt(cursor.getColumnIndex(COURSE_IS_ACTIVE)));
                courseList.add(course);
            }while(cursor.moveToNext());
            if(cursor!=null){
                cursor.close();;
            }
            databaseHelper.closeDatabase();
        }
        return courseList;
    }

    public ArrayList<QuestionOptions.Option> getOptions(int questionId){
        DatabaseHelper helper = DatabaseHelper.getInstance();
        String query = "select * from " + TABLE_CHOICE + " where " + CHOICE_QUESTION_ID + "=?";
        SQLiteDatabase sqLiteDatabase = helper.openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(questionId)});

        ArrayList<QuestionOptions.Option> list = new ArrayList<>();
        if(cursor != null) {
            while (cursor.moveToNext()) {
                QuestionOptions.Option option = new QuestionOptions.Option();
                option.setOptionName(cursor.getString(cursor.getColumnIndex(CHOICE_NAME)));
                option.setOptionId(cursor.getInt(cursor.getColumnIndex(CHOICE_ID)));
                option.setQuestionId(cursor.getInt(cursor.getColumnIndex(CHOICE_QUESTION_ID)));
                option.setIsRightAnswer(cursor.getInt(cursor.getColumnIndex(CHOICE_IS_RIGHT_CHOICE)) == 1);
                list.add(option);
            }
            cursor.close();
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<Question> getQuizQuestions(int weekID, int courseID){
        databaseHelper = DatabaseHelper.getInstance();
        List<Question> cardsList=new ArrayList<>();
        String qry = "select * from " + TABLE_QUESTION + " where " + QUESTION_WEEK_ID + " = ? and " + QUESTION_COURSE_ID + " = ? ";
        SQLiteDatabase database = databaseHelper.openDatabase();
        Cursor cursor=null;
        try {

            cursor = database.rawQuery(qry, new String[]{String.valueOf(weekID), String.valueOf(courseID)});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    //CREATE TABLE Questions(QuestionId integer primary key,QuestionName text,isActive bool, "WeekID" INTEGER, "CourseID" INTEGER)
                    Question question = new Question();
                    question.setId(cursor.getInt(cursor.getColumnIndex(QUESTION_ID)));
                    question.setName(cursor.getString(cursor.getColumnIndex(QUESTION_NAME)));
                    question.setWeekId(cursor.getInt(cursor.getColumnIndex(QUESTION_WEEK_ID)));
                    question.setCourseId(cursor.getInt(cursor.getColumnIndex(QUESTION_COURSE_ID)));
                    cardsList.add(question);

                }
                while (cursor.moveToNext());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            {
                if (cursor != null) {
                    cursor.close();
                    ;
                }
                databaseHelper.closeDatabase();
            }
        }
        return cardsList;
    }

    public void updateCourse(int courseID,int isActive){
        databaseHelper = DatabaseHelper.getInstance();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("update " + TABLE_COURSE + " set " + COURSE_IS_ACTIVE + " = " + isActive + " where " + COURSE_ID + " = " + courseID);
        String qry=stringBuilder.toString();
        SQLiteDatabase database = databaseHelper.openDatabase();
         database.execSQL(qry);
        databaseHelper.closeDatabase();
    }

}

