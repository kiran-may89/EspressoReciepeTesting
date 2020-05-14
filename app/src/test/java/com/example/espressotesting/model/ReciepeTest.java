package com.example.espressotesting.model;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class ReciepeTest {

    @Test
    public void readReceipe(){
        InputStream stream  = ReciepeTest.class.getResourceAsStream("/recipes/water.txt");
        Reciepe reciepe = Reciepe.readReceipe(stream);
        assertNotNull(reciepe);
        assertEquals(reciepe.getId(),"water");
        assertEquals(reciepe.getTitle(),"Water");
        assertEquals(reciepe.getDesc(),"Put glass under tap. Open tap. Close tap. Drink.");

    }

}