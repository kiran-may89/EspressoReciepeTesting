package com.example.espressotesting.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceipeStore {

    public final List<Reciepe> recipes = new ArrayList<>();
    private HashMap<String, Reciepe> table  = new HashMap<>();

    public ReceipeStore(Context context, String directory) {
        List<InputStream> streams = getInputStream(context.getAssets(), directory);

        for (InputStream stream : streams) {
            Reciepe recipe = Reciepe.readReceipe(stream);
            if (recipe != null) {
                recipes.add(recipe);
                table.put(recipe.getId(),recipe);
            }
        }
    }


    public List<InputStream> getInputStream(AssetManager manager, String directory) {
        List<InputStream> streams = new ArrayList<>();
        String[] fileNames = getnames(manager, directory);
        for (String fileName : fileNames) {
            try {

                File file = new File(directory, fileName);
                InputStream stream = manager.open(file.getPath());
                streams.add(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return streams;
    }

    public String[] getnames(AssetManager manager,String directory) {
        if (directory == null) {
            return new String[0];
        }
        try {
            return manager.list(directory);
        } catch (IOException e) {
            return new String[0];
        }

    }

    public Reciepe getRecipe(String id) {
        return  table.get(id);
    }
}
