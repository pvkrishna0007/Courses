package com.tenhrs.courses.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tenhrs.courses.model.Course;

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

}

