package com.example.espressotesting;

import android.os.Bundle;

import androidx.test.runner.AndroidJUnitRunner;

import com.example.espressotesting.data.InMemorySharedPref;
import com.example.espressotesting.data.SharedMemory;

public class TestingApplication extends App {

    private SharedMemory sharedMemory ;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedMemory  = new InMemorySharedPref();
    }

    @Override
    public SharedMemory getSharedMemory() {
        return sharedMemory;
    }
}
