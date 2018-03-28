package com.example.kashfatahir.todolistappproject;

/**
 * Created by kashfatahir on 26/03/2018.
 */

public class Task {

        int id;
        String task, priority;
        boolean completed;

        public Task(int id, String task, String priority, int completed) {
            this.id = id;
            this.task = task;
            this.priority = priority;
            this.completed = (completed == 1);

        }

        public int getId() {
            return id;
        }

        public String getTask() {
            return task;
        }

        public String getPriority() {
            return priority;
        }

        public boolean getCompleted() {return completed; }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }


    }




