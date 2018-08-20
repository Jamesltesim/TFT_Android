package com.example.james.tft_android;

import android.app.Application;

public class App extends Application{

    public static App getApplication() {
        return application;
    }

    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
}
