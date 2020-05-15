package com.example.espressotesting.ui;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    public final TextView textView;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
}
