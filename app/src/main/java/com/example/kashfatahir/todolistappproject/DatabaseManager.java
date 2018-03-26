package com.example.kashfatahir.todolistappproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.RenderScript;

/**
 * Created by kashfatahir on 26/03/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TODOLISTAPPPROJECT";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "TaskName";
    private static final String COLUMN_PRIORITY = "priority";



    public DatabaseManager(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +

                " "+ COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT, \n" +
                " "+ COLUMN_NAME + " varchar(200) NOT NULL, \n" +
                " "+ COLUMN_PRIORITY + "varchar(200) NOT NULL\n" +

                ");";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

        Boolean addTask(String TaskName, String priority) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, TaskName);
        cv.put(COLUMN_PRIORITY, priority);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv ) != -1;

        }

    Cursor getAllEmployees() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }


    }

