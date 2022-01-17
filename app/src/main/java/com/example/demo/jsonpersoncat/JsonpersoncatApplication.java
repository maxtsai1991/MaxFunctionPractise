package com.example.demo.jsonpersoncat;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class JsonpersoncatApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
