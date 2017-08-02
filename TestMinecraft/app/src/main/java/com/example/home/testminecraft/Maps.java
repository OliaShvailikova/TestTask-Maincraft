package com.example.home.testminecraft;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Maps {
    private String name_of_map;
    private String text_about_map;
    private String map;
    private String[] maps;
    private int i;
    private boolean video;

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public Maps(int i, Context context) {
        this.i = i;
        text_about_map = readText(context, i);
        AssetManager assetManager = context.getAssets();
        try {
            maps = assetManager.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        map = maps[i+1];
        name_of_map = readName(context, i);
       // video = true;

    }

    private String readText(Context context, int i) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.text1 + i);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder text = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
                text.append('\n');

            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    private String readName(Context context, int i) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.text1 + i);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        try {
            line = bufferedReader.readLine();

        } catch (IOException e) {
            return null;
        }
        return line;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getText_about_map() {

        return text_about_map;
    }

    public void setText_about_map(String text_about_map) {
        this.text_about_map = text_about_map;
    }

    public String getName_of_map() {

        return name_of_map;
    }

    public void setName_of_map(String name_of_map) {
        this.name_of_map = name_of_map;
    }
}
