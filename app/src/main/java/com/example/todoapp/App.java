package com.example.todoapp;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.todoapp.Utilities.MySp;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySp.init(this);
        MySp.getInstance().claerData();
    }
}
