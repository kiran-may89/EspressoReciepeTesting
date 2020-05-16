package com.example.espressotesting.data;

public interface SharedMemory {
     boolean get(String id);
     boolean toggle(String id);
}
