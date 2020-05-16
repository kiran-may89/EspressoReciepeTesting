package com.example.espressotesting.data;

import android.content.SharedPreferences;

import java.util.HashMap;

public class InMemorySharedPref implements SharedMemory {

private HashMap<String, Object> inMemoryHashMap;
    public InMemorySharedPref() {
        inMemoryHashMap = new HashMap<>();
    }

    @Override
    public boolean get(String id) {

        return inMemoryHashMap.get(id) != null && (boolean) inMemoryHashMap.get(id);
    }

    public void put(String id, boolean favorite) {
        inMemoryHashMap.put(id, favorite);

    }

    @Override
    public boolean toggle(String id) {
        boolean favourite = get(id);
        put(id,!favourite);
        return !favourite;
    }

    public void clear() {
        inMemoryHashMap.clear();
    }
}
