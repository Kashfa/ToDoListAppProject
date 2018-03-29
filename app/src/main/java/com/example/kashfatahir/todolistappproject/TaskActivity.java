package com.example.kashfatahir.todolistappproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    DatabaseManager mDatabase;

    List<Task> TaskList;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        mDatabase =  new DatabaseManager(this);

        TaskList = new ArrayList<>();

        listview = (ListView) findViewById(R.id.listViewTasks);


        loadTaskFromDatabase();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.activity_main, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.view_tasks:
//                Intent intent = new Intent(this, TaskActivity.class);
//                startActivity(intent);
//        }
//        switch (item.getItemId()) {
//            case R.id.add_task:
////                addTaskPopup();
//
//        }
//        return true;
//    }

//    private void addTaskPopup() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
//
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//
//        View view = inflater.inflate(R.layout.dialog_update_task, null);
//
//        builder.setView(view);
//
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//        final EditText editTextTask = view.findViewById(R.id.addtask);
//        final Spinner spinner = view.findViewById(R.id.spinnerPriority);
//    }



    private void loadTaskFromDatabase(){

        Cursor cursor = mDatabase.getAllTasks();

        if(cursor.moveToFirst()) {
            do {
                TaskList.add(new Task (
                                cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4)
                ));

            } while (cursor.moveToNext());

            TaskAdapter adapter = new TaskAdapter(this,R.layout.list_layout_task, TaskList, mDatabase);
            listview.setAdapter(adapter);
        }



    }
}

