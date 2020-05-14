package com.example.espressotesting.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reciepe {
    private String id;
    private String title;
    private String desc;
    private static final String ID_PREFIX = "id=";
    private static final String TITLE_PREFIX = "title=";

    public Reciepe(String id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public static Reciepe readReceipe(InputStream stream) {
        StringBuilder builder = new StringBuilder();
        String id = null;
        String title = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(ID_PREFIX)) {
                    id = line.substring(ID_PREFIX.length());
                    continue;
                }
                if (line.startsWith(TITLE_PREFIX)) {
                    title = line.substring(TITLE_PREFIX.length());
                    continue;
                }
                if (builder.length() > 0) {
                    builder.append("\n");
                }
                builder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Reciepe(id, title, builder.toString());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}

