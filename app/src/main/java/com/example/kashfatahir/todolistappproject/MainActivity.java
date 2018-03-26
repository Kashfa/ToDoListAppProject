package com.example.kashfatahir.todolistappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "mydatabase";

    DatabaseManager mDatabase;

    EditText editTask;
    Spinner spinnerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DatabaseManager(this);

        editTask = (EditText) findViewById(R.id.editTask);
        spinnerPriority = (Spinner) findViewById(R.id.spinnerPriority);

        findViewById(R.id.buttonAddTask).setOnClickListener(this);
//        findViewById(R.id.textViewViewEmployees).setOnClickListener(this);
    }

    private void addEmployee() {
        String task = editTask.getText().toString().trim();
        String priority = spinnerPriority.getSelectedItem().toString();

        if (task.isEmpty()) {
            editTask.setError("Task can't be empty");
            editTask.requestFocus();
            return;
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        if (mDatabase.addTask(task, priority ))
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddTask:

                addEmployee();

                break;
            case R.id.textViewViewTasks:

                startActivity(new Intent(this, TaskActivity.class));
                break;

        }
    }
}
