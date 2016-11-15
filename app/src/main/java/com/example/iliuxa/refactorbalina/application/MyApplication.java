package com.example.iliuxa.refactorbalina.application;

import android.app.Application;

import com.example.iliuxa.refactorbalina.model.DataBaseFactory;


public class MyApplication extends Application {
    public static final String TAG = "ERROR";

    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseFactory.setHelper(getApplicationContext());
    }

}
