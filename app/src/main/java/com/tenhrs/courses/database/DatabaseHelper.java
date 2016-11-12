package com.tenhrs.courses.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tenhrs.courses.common.Constants;

/**
 * Created by SIVA on 27-05-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;

    private static final String DATABASE_NAME = Constants.DB_NAME;
    private static final String DATABASE_TABLE = "tableName";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public static DatabaseHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    private DatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"Courses\" (\"CourseID\" INTEGER PRIMARY KEY  NOT NULL ,\"CourseName\" TEXT, \"isActive\" INTEGER)");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS);

        // Create tables again
//        onCreate(db);
    }

    public void insertCoursesData() {

    }

}
