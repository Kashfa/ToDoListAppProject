package com.example.kashfatahir.todolistappproject;

/**
 * Created by kashfatahir on 26/03/2018.
 */

public class Task {

        int id;
        String task, priority;



        public Task(int id, String task, String Priority) {
            this.id = id;
            this.task = task;
            this.priority = priority;

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


    }




