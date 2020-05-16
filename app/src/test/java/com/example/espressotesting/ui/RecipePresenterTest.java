package com.example.espressotesting.ui;

import com.example.espressotesting.data.SharedMemory;
import com.example.espressotesting.model.ReceipeStore;
import com.example.espressotesting.model.Reciepe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.InputStream;

import static org.junit.Assert.*;

public class RecipePresenterTest {
    private RecipePresenter presenter;
    private ReceipeStore store;
    RecipeContract.View view;
    SharedMemory favorite;
    @Before
    public void setup(){
        favorite = Mockito.mock(SharedMemory.class);
        view = Mockito.mock(RecipeContract.View.class);
        store = Mockito.mock(ReceipeStore.class);
        presenter = new RecipePresenter(store,view,favorite);

    }

    @Test
    public void no_receipe_test(){
        Reciepe reciepe = Mockito.mock(Reciepe.class);
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(null);
        presenter.loadRecipe("NO_RECEIPE");
        Mockito.verify(view,Mockito.times(1)).showRecipeNotFoundError();
    }

    @Test
    public void toggle(){
        presenter.toggleFavorite();
    }

    @Test
    public void loadWater(){

        InputStream stream = RecipePresenterTest.class.getResourceAsStream("/recipes/mixed.txt");

        Reciepe reciepe = Reciepe.readReceipe(stream);
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(reciepe);
        Mockito.when(favorite.toggle(Mockito.anyString())).thenReturn(true);
        presenter.loadRecipe("punch");
        presenter.toggleFavorite();
        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(view,Mockito.times(2)).setFavorite(captor.capture());
        assertFalse(captor.getAllValues().get(0));
        assertTrue(captor.getAllValues().get(1));



    }
}