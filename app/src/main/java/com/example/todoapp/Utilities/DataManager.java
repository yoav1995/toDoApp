package com.example.todoapp.Utilities;

import com.example.todoapp.Models.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DataManager {


    public static ArrayList<Task> getTasks() {
        ArrayList<Task> tasks;
        /// -----------------1---------------
        /*
        tasks.add(new Task()
                .setTask("Buy Fruits")
        );
        */
        String fromSP = MySp.getInstance().getString("usersDetails","");
        tasks= new Gson().fromJson(fromSP,new TypeToken<ArrayList<Task>>(){}.getType());
        return tasks == null ? new ArrayList<Task>() : tasks;
}

}
