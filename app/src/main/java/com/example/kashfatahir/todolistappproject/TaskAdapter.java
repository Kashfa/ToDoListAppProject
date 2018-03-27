package com.example.kashfatahir.todolistappproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kashfatahir on 26/03/2018.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    Context mCtx;
    int layoutRes;
    List<Task> taskList;
    DatabaseManager mDatabase;

    public TaskAdapter(Context mCtx, int layoutRes, List<Task> taskList, DatabaseManager mDatabase) {
        super(mCtx, layoutRes, taskList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.taskList = taskList;
        this.mDatabase = mDatabase;
    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(layoutRes, null);

        final Task task = taskList.get(position);

        TextView textViewTask = view.findViewById(R.id.textViewTask);
        TextView textViewPriority = view.findViewById(R.id.textViewPriority);

        textViewTask.setText(task.getTask());
        textViewPriority.setText(task.getPriority());

        Button buttonDelete = view.findViewById(R.id.buttonDeleteTask);
        Button buttonEdit = view.findViewById(R.id.buttonEditTask);
        buttonEdit.setOnClickListener(new View.OnClickListener() {

            //      view.findViewById(R.id.buttonUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask(task);
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mDatabase.deleteTask(task.getId()))
                            loadTaskFromDatabaseAgain();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private void updateTask(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.dialog_update_task, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextTask = view.findViewById(R.id.editTask);
        final Spinner spinner = view.findViewById(R.id.spinnerPriority);


        editTextTask.setText(task.getTask());

        view.findViewById(R.id.buttonUpdateTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextTask.getText().toString().trim();
                String dept = spinner.getSelectedItem().toString();


                if (name.isEmpty()) {
                    editTextTask.setError("Task can't be empty");
                    editTextTask.requestFocus();
                    return;
                }



                if(mDatabase.updateTask(task.getId(), name, dept)) {
                    Toast.makeText(mCtx, "Task Updated", Toast.LENGTH_SHORT).show();
                    loadTaskFromDatabaseAgain();
                }else{

                    Toast.makeText(mCtx, "Task not Updated", Toast.LENGTH_SHORT).show();

                }
                alertDialog.dismiss();


            }
        });
    }

    private void loadTaskFromDatabaseAgain() {

        Cursor cursor = mDatabase.getAllTasks();


        if (cursor.moveToFirst()) {
            taskList.clear();
            do {
                taskList.add(new Task(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)

                ));

            } while (cursor.moveToNext());

            cursor.close();

            notifyDataSetChanged();

        }
    }

}











