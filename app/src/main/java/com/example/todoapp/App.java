package com.example.todoapp;

import android.app.Application;

import com.example.todoapp.Utilities.MySp;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySp.init(this);
    }
}
