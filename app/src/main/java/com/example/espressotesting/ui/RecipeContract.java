package com.example.espressotesting.ui;

public interface RecipeContract {
    interface View {
        void showRecipeNotFoundError();
        void setTitle(String title);
        void setDescription(String description);
        void setFavorite(boolean favorite);
    }
    interface Listener {

    }
}
