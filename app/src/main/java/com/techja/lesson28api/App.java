package com.techja.lesson28api;

import android.app.Application;

public class App extends Application {
    private static App instance;
    private String text;
    private Storage storage;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new Storage();
    }

    public Storage getStorage() {
        return storage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
