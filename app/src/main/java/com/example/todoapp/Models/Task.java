package com.example.todoapp.Models;

public class Task {

    private String task;
    private int priority;
    private boolean isDone;

    public String getTask() {
        return task;
    }

    public int getPriority() {
        return priority;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public Task setTask(String task){
        this.task=task;
        return this;
    }

    public Task setPriority(int priority)
    {
        this.priority=priority;
        return this;
    }


}
