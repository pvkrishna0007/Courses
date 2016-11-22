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
public class CourseDB {
    private Context context;
    private DatabaseHelper databaseHelper;

    public CourseDB(Context context){
        this.context=context;
    }

    public List<Course> getCourses(int isActive){
        databaseHelper = DatabaseHelper.getInstance();
        List<Course> courseList=new ArrayList<Course>();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("select * from Courses");
        if(isActive==1){
            stringBuilder.append(" where isActive= "+isActive);
        }
        String qry=stringBuilder.toString();
        SQLiteDatabase database = databaseHelper.openDatabase();
        Cursor cursor = database.rawQuery(qry,null);
        if(cursor!=null&&cursor.moveToFirst()){
            do{
                Course course=new Course();
                course.setCourseID(cursor.getInt(cursor.getColumnIndex("CourseID")));
                course.setCourseName(cursor.getString(cursor.getColumnIndex("CourseName")));
                course.setIsActive(cursor.getInt(cursor.getColumnIndex("isActive")));
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
        String query="select * from QuestionChoice where QuestionID=?";
        SQLiteDatabase sqLiteDatabase = helper.openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(questionId)});

        ArrayList<QuestionOptions.Option> list = new ArrayList<>();
        if(cursor != null) {
            while (cursor.moveToNext()) {
                QuestionOptions.Option option = new QuestionOptions.Option();
                option.setOptionName(cursor.getString(cursor.getColumnIndex("ChoiceName")));
                option.setOptionId(cursor.getInt(cursor.getColumnIndex("ChiceID")));
                option.setQuestionId(cursor.getInt(cursor.getColumnIndex("QuestionID")));
                option.setIsRightAnswer(cursor.getInt(cursor.getColumnIndex("IsRightChoice"))==1);
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
        String qry="select * from Questions where WeekID = ? and CourseID = ? ";
        SQLiteDatabase database = databaseHelper.openDatabase();
        Cursor cursor=null;
        try {

            cursor = database.rawQuery(qry, new String[]{String.valueOf(weekID), String.valueOf(courseID)});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    //CREATE TABLE Questions(QuestionId integer primary key,QuestionName text,isActive bool, "WeekID" INTEGER, "CourseID" INTEGER)
                    Question question = new Question();
                    question.setId(cursor.getInt(cursor.getColumnIndex("QuestionId")));
                    question.setName(cursor.getString(cursor.getColumnIndex("QuestionName")));
                    question.setWeekId(cursor.getInt(cursor.getColumnIndex("WeekID")));
                    question.setCourseId(cursor.getInt(cursor.getColumnIndex("CourseID")));
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
        List<Course> courseList=new ArrayList<Course>();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("update  Courses set isActive= "+isActive+" where CourseID="+courseID);
        String qry=stringBuilder.toString();
        SQLiteDatabase database = databaseHelper.openDatabase();
         database.execSQL(qry);
        databaseHelper.closeDatabase();
    }

}

