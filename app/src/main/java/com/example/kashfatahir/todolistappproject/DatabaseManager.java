package com.example.kashfatahir.todolistappproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.RenderScript;
import android.util.Log;

/**
 * Created by kashfatahir on 26/03/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TODOLISTAPPPROJECT";
    private static final int DATABASE_VERSION = 6;
    private static final String TABLE_NAME = "Tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "TaskName";
    private static final String COLUMN_PRIORITY = "priority";
    private static final String COLUMN_COMPLETED = "completed";



    public DatabaseManager(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//       String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
//
//                " "+ COLUMN_ID + " INTEGER  PRIMARY KEY, \n" +
//                " "+ COLUMN_NAME + " varchar(200) NOT NULL, \n" +
//                " "+ COLUMN_PRIORITY + "varchar(200) NOT NULL\n" +
//
//                ");";

        String sql = "CREATE TABLE " + TABLE_NAME +  " (" +

                 COLUMN_ID + " INTEGER PRIMARY KEY," +
                 COLUMN_NAME + " varchar(200) NOT NULL," +
                COLUMN_PRIORITY + " varchar(200) NOT NULL, " +
                COLUMN_COMPLETED + " INTEGER NOT NULL);";


        Log.e("Table", sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public Boolean addTask(String TaskName, String priority) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, TaskName);
        cv.put(COLUMN_PRIORITY, priority);
        cv.put(COLUMN_COMPLETED, false);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv ) != -1;
    }

    Cursor getAllTasks() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean updateTask(int id, String TaskName, String priority, boolean completed){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, TaskName);
        cv.put(COLUMN_PRIORITY, priority);
        cv.put(COLUMN_COMPLETED, completed);

        return sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID+"=?", new String[]{String.valueOf(id)})>0;

    }

    boolean deleteTask(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)})>0;

    }

}




