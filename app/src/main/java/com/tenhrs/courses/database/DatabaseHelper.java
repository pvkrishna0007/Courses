package com.tenhrs.courses.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tenhrs.courses.common.Constants;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by SIVA on 27-05-2016.
 */
public class DatabaseHelper {

    private static DatabaseHelper mInstance = null;

    private SQLiteDatabase mDatabase;
    private static DatabaseHelper sDataBaseHandler;
    private AtomicInteger mOpenCounter = new AtomicInteger();


    public static synchronized DatabaseHelper getInstance() {
        if (sDataBaseHandler == null) {
            sDataBaseHandler = new DatabaseHelper();
        }
        return sDataBaseHandler;
    }

    public synchronized SQLiteDatabase openDatabase() {
        try {
            if (mOpenCounter.incrementAndGet() == 1) {
                if (mDatabase == null) {
                    mDatabase = SQLiteDatabase.openDatabase(Constants.DB_PATH, null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
                } else if (!mDatabase.isOpen()) {
                    mDatabase = SQLiteDatabase.openDatabase(Constants.DB_PATH, null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
                }
            }
        }catch(Exception e){
            e.printStackTrace();


        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            if (mDatabase != null) {
                mDatabase.close();
            }
        }
    }

    public ArrayList<String> pragmainfo(String tablename) {
        ArrayList<String> pragma = new ArrayList<String>();
        openDatabase();
        String pragmaquery = "pragma table_info(" + tablename + ")";
        Cursor mCursor = mDatabase.rawQuery(pragmaquery, null);
        try {

            if (mCursor.moveToFirst()) {
                do {
                    pragma.add(mCursor.getString(1));

                } while (mCursor.moveToNext());
            }

        } catch (Exception e) {
        }
        finally
        {
            closeDatabase();
        }

        return pragma;
    }

}
