package com.example.espressotesting.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.espressotesting.App;
import com.example.espressotesting.R;
import com.example.espressotesting.data.SharedMemory;
import com.example.espressotesting.data.SharedPreferencesFavorites;
import com.example.espressotesting.model.ReceipeStore;
import com.example.espressotesting.model.Reciepe;

public class RecipeActivity extends AppCompatActivity {
    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        final TextView titleView = (TextView) findViewById(R.id.title);
        TextView descriptionView = (TextView) findViewById(R.id.description);

        ReceipeStore store = new ReceipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        final Reciepe recipe = store.getRecipe(id);

        if (recipe == null) {
            titleView.setVisibility(View.GONE);
            descriptionView.setText(R.string.recipe_not_found);
            return;
        }

        final SharedMemory favorites = ((App)getApplication()).getSharedMemory();
        boolean favorite = favorites.get(recipe.getId());

        titleView.setText(recipe.getTitle());
        titleView.setSelected(favorite);
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = favorites.toggle(recipe.getId());
                titleView.setSelected(result);
            }
        });
        descriptionView.setText(recipe.getDesc());
    }
}
