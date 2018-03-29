package com.example.kashfatahir.todolistappproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by kashfatahir on 29/03/2018.
 */

public class TaskDescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);

        setContentView(R.layout.task_description);

        EditText editText = findViewById(R.id.taskDescription);

        Task task = (Task) getIntent().getSerializableExtra("task");
        editText.setText(task.getDescription());
    }
}
