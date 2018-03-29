package com.example.kashfatahir.todolistappproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseManager mDatabase;

    EditText editTask;
    Spinner spinnerPriority;
    EditText taskDescription;
    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DatabaseManager(this);

        mCtx = this;

        editTask = (EditText) findViewById(R.id.editTask);
        spinnerPriority = (Spinner) findViewById(R.id.spinnerPriority);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_tasks:
                Intent intent = new Intent(this, TaskActivity.class);
                startActivity(intent);
        }
        switch (item.getItemId()) {
            case R.id.add_task:
              addTaskPopup();

        }
        return true;
    }

    private void addTaskPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.dialog_update_task, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextTask = view.findViewById(R.id.addtask);
        final Spinner spinner = view.findViewById(R.id.spinnerPriority);



        view.findViewById(R.id.buttonAddTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String task = editTextTask.getText().toString().trim();
                String priority = spinner.getSelectedItem().toString();
                String description = "Please add a description";


                if (task.isEmpty()) {
                    editTextTask.setError("Task can't be empty");
                    editTextTask.requestFocus();
                    return;
                }



                if(mDatabase.addTask(task, priority, description)) {
                    Toast.makeText(mCtx, "Task Added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mCtx, "Task not Added", Toast.LENGTH_SHORT).show();

                }
                alertDialog.dismiss();


            }
        });

    }


    private void addTask() {
        String task = editTask.getText().toString().trim();
        String priority = spinnerPriority.getSelectedItem().toString();
        String description = editTask.getText().toString();

        if (task.isEmpty()) {
            editTask.setError("Task can't be empty");
            editTask.requestFocus();
            return;
        }


        if (mDatabase.addTask(task, priority, description))
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();

    }

    private void addDescription(){
        String description = editTask.getText().toString();
    }

    public void onClick(View view) {


        Log.e("MainActivity", "clicked");

        switch (view.getId()) {
            case R.id.textViewTask:

                addDescription();

                break;
            case R.id.taskDescription:

                startActivity(new Intent(this, TaskActivity.class));
                break;

        }


        }


    }






