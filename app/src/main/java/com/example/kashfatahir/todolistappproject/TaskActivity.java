package com.example.kashfatahir.todolistappproject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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

    private void loadTaskFromDatabase(){

        Cursor cursor = mDatabase.getAllEmployees();

        if(cursor.moveToFirst()) {
            do {
                TaskList.add(new Task (
                                cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)

                ));

            } while (cursor.moveToNext());

            EmployeeAdapter adapter = new EmployeeAdapter(this,R.layout.list_layout_employees, employeeList, mDatabase);
            listview.setAdapter(adapter);
        }

    }
}
    }
}