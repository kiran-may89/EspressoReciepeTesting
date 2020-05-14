package com.example.espressotesting.model;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;


public class ReceipeStoreTest {

    @Test
    public void nullDirectory(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ReceipeStore store = new ReceipeStore(context,null);
        assertNotNull(store);
        assertNotNull(store.recipes);
        assertEquals(0,store.recipes.size());
    }
    @Test
    public void testReceipeStore(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ReceipeStore store = new ReceipeStore(context,"recipes");
        assertNotNull(store);
        assertNotNull(store.recipes);
        assertEquals(4,store.recipes.size());
    }

}