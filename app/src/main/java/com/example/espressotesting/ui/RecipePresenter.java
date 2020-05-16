package com.example.espressotesting.ui;

import android.view.View;

import com.example.espressotesting.data.SharedMemory;
import com.example.espressotesting.model.ReceipeStore;
import com.example.espressotesting.model.Reciepe;


public class RecipePresenter implements RecipeContract.Listener {
    private final ReceipeStore store;
    private final RecipeContract.View view;
    private final SharedMemory favorites;
    private Reciepe recipe;

    public RecipePresenter(ReceipeStore store, RecipeContract.View view, SharedMemory favorites) {
        this.store = store;
        this.view = view;
        this.favorites = favorites;
    }

    public void loadRecipe(String id) {
        recipe = store.getRecipe(id);
        if (recipe == null) {
            view.showRecipeNotFoundError();

        } else {
            view.setTitle(recipe.getTitle());
            view.setDescription(recipe.getDesc());
            view.setFavorite(favorites.get(recipe.getId()));
        }
    }

    public void toggleFavorite() {
        if (recipe == null) {
            throw new IllegalStateException();
        }
        boolean result = favorites.toggle(recipe.getId());
        view.setFavorite(result);
    }
}
