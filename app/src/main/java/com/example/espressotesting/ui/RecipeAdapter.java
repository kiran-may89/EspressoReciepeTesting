package com.example.espressotesting.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.espressotesting.R;
import com.example.espressotesting.model.ReceipeStore;
import com.example.espressotesting.model.Reciepe;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private final ReceipeStore store;

    public RecipeAdapter(ReceipeStore store) {
        this.store = store;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, int position) {
        final Reciepe recipe = store.recipes.get(position);
        holder.textView.setText(recipe.getTitle());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.textView.getContext();
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra(RecipeActivity.KEY_ID, recipe.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return store.recipes.size();
    }
}
