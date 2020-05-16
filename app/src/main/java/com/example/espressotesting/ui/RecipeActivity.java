package com.example.espressotesting.ui;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.espressotesting.App;
import com.example.espressotesting.R;
import com.example.espressotesting.data.SharedMemory;
import com.example.espressotesting.model.ReceipeStore;


public class RecipeActivity extends AppCompatActivity implements RecipeContract.View {
    public static final String KEY_ID = "id";
    private TextView titleView;
    private TextView descriptionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Step 1: Set up the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        titleView = (TextView) findViewById(R.id.title);
        descriptionView = (TextView) findViewById(R.id.description);

        // Step 2: Load recipe from store
        ReceipeStore store = new ReceipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        App app = (App) getApplication();
        final SharedMemory favorites = app.getSharedMemory();
        final RecipePresenter presenter = new RecipePresenter(store, this, favorites);
        presenter.loadRecipe(id);

        // Step 3: If recipe is null, show error. This is done in the presenter

        // Step 4: If recipe is not null, show recipe. This is done in the presenter

        // Step 5: When title is clicked, toggle favorites
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.toggleFavorite();
            }
        });
    }

    @Override
    public void showRecipeNotFoundError() {
        titleView.setVisibility(View.GONE);
        descriptionView.setText(R.string.recipe_not_found);
    }

    @Override
    public void setTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void setDescription(String description) {
        descriptionView.setText(description);
    }

    @Override
    public void setFavorite(boolean favorite) {
        titleView.setSelected(favorite);
    }
}
