package com.example.espressotesting;

import android.app.Application;

import com.example.espressotesting.data.SharedMemory;
import com.example.espressotesting.data.SharedPreferencesFavorites;

public class App extends Application {
    SharedPreferencesFavorites favorites;
    @Override
    public void onCreate() {
        super.onCreate();
        favorites = new SharedPreferencesFavorites(this);
    }

    public SharedMemory getSharedMemory(){
        return  favorites;
    }
}
