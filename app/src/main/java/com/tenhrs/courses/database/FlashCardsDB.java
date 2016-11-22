package com.tenhrs.courses.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tenhrs.courses.model.FlashCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIVA on 15-11-2016.
 */

public class FlashCardsDB {
    private Context context;
    private DatabaseHelper databaseHelper;

    public FlashCardsDB(Context context){
        this.context=context;
    }

    public List<FlashCard> getFlashCards(int weekID,int courseID){
        databaseHelper = DatabaseHelper.getInstance();
        List<FlashCard> cardsList=new ArrayList<FlashCard>();
        StringBuilder stringBuilder=new StringBuilder();
        String qry="select q.QuestionId ,ChoiceName ,q.QuestionName from Questions q " +
                "inner join QuestionChoice qc  on qc.QuestionId =q.QuestionId   where q.WeekID = "+weekID+" and q.CourseID = "+courseID+" and " +
                "qc.IsRightChoice=1";
        SQLiteDatabase database = databaseHelper.openDatabase();
        Cursor cursor=null;
        try {

             cursor = database.rawQuery(qry, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    FlashCard flashCard = new FlashCard();
                    flashCard.setQuestionID(cursor.getInt(cursor.getColumnIndex("QuestionId")));
                    flashCard.setChoiceName(cursor.getString(cursor.getColumnIndex("ChoiceName")));
                    flashCard.setQuestion(cursor.getString(cursor.getColumnIndex("QuestionName")));
                    cardsList.add(flashCard);

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
}
