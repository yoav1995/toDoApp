package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.todoapp.Adapters.TaskAdapter;
import com.example.todoapp.Models.Task;
import com.example.todoapp.Utilities.DataManager;
import com.example.todoapp.Utilities.MySp;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView lst_tasks;
    private AppCompatEditText task_editor,priority_editor;
    private MaterialButton add_task;
    private MaterialTextView simple_text;

    private StringRequest stringRequest;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        savedRecycleredView();
        //sendStringRequest(simple_text,requestQueue,stringRequest);
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
        simple_text=findViewById(R.id.simple_text);
    }

    private void sendStringRequest(MaterialTextView text,RequestQueue queue,StringRequest stringRequest)
    {
        queue = Volley.newRequestQueue(this);
        String url = "https://www.google.com";

// Request a string response from the provided URL.
       stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        BreakIterator textView;
                        text.setText("Response is: " + response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                BreakIterator textView;
                text.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }



    private void cancelRequestQueue(RequestQueue requestQueue)
    {
        requestQueue.cancelAll(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        cancelRequestQueue(requestQueue);
    }
}