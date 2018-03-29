package com.example.kashfatahir.todolistappproject;

import java.io.Serializable;

/**
 * Created by kashfatahir on 26/03/2018.
 */

public class Task implements Serializable {

        int id;
        String task, priority, description;
        boolean completed;

    public Task(int id, String task, String priority, int completed, String description) {
            this.id = id;
            this.task = task;
            this.priority = priority;
            this.completed = (completed == 1);
            this.description = description;

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

        public boolean getCompleted() {

            return completed;
        }

        public void setCompleted(boolean completed) {


            this.completed = completed;
        }

    public String getDescription() {

            return description;
        }
}




