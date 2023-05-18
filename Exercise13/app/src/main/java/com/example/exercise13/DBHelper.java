package com.example.exercise13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.exercise13.FeedEntry.COLUMN_NAME_ID;
import static com.example.exercise13.FeedEntry.COLUMN_NAME_NUMBER;
import static com.example.exercise13.FeedEntry.DATABASE_NAME;
import static com.example.exercise13.FeedEntry.DATABASE_VERSION;
import static com.example.exercise13.FeedEntry.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " CHAR(20) PRIMARY KEY," +
                    COLUMN_NAME_NUMBER + " INTEGER" +
                    ")";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
