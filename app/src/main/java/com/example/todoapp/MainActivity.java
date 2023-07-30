package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.todoapp.Adapters.TaskAdapter;
import com.example.todoapp.Models.Task;
import com.example.todoapp.Utilities.DataManager;
import com.example.todoapp.Utilities.MySp;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView lst_tasks;
    private AppCompatEditText task_editor,priority_editor;
    private MaterialButton add_task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        savedRecycleredView();
        // listener for adding tasks
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task=task_editor.getText().toString();
                int priority =Integer.parseInt(priority_editor.getText().toString());
                addTasks(task,priority);
            }
        });

    }

    private void savedRecycleredView() {
        if(!DataManager.getTasks().isEmpty()) {
            ArrayList<Task> tasks = DataManager.getTasks();
            //update tasks list
            for(Task task: tasks) {
                if(task.isDone())
                    tasks.remove(task);
            }

            tasks.sort((task1, t2) -> Integer.compare(t2.getPriority(), task1.getPriority()));
            TaskAdapter tasksAdapter=new TaskAdapter(getApplicationContext(),tasks);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            lst_tasks.setLayoutManager(linearLayoutManager);
            lst_tasks.setAdapter(tasksAdapter);
        }
    }

    private void addTasks(String task,int priority) {
        ArrayList<Task> tasks = DataManager.getTasks();
        //adding new task
        tasks.add(new Task().
                setTask(task)
                .setPriority(priority));
        //update tasks list
        tasks.removeIf(Task::isDone);
        tasks.sort((task1, t2) -> Integer.compare(t2.getPriority(), task1.getPriority()));
        String highScoreJson=new Gson().toJson(tasks);
        MySp.getInstance().putString("usersDetails",highScoreJson);
        TaskAdapter tasksAdapter=new TaskAdapter(getApplicationContext(),tasks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        lst_tasks.setLayoutManager(linearLayoutManager);
        lst_tasks.setAdapter(tasksAdapter);
    }

    private void findViews() {
        lst_tasks=findViewById(R.id.lst_tasks);
        task_editor=findViewById(R.id.task_editor);
        priority_editor=findViewById(R.id.priority_editor);
        add_task=findViewById(R.id.add_task);
    }



}