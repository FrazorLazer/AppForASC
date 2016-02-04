package com.example.frazzle.appforasc;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Frazzle on 13/01/2016.
 */
public class ExtendedApp extends Application{

    private Character characterOne = new Character("","","","");
    private Character characterTwo = new Character("","","","");;
    private String story ="Sleepover";
    private String storyFormat = "Options";
    private int storyProgress = 1;
    private String pathname = "";

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getStoryFormat() {
        return storyFormat;
    }

    public void setStoryFormat(String storyFormat) {
        this.storyFormat = storyFormat;
    }

    public Character getCharacterOne() {
        return characterOne;
    }

    public Character getCharacterTwo() {
        return characterTwo;
    }

    public String getStory() {
        return story;
    }

    public int getStoryProgress() {
        return storyProgress;
    }

    public void setCharacterOne(Character characterOne) {
        this.characterOne = characterOne;
    }

    public void setCharacterTwo(Character characterTwo) {
        this.characterTwo = characterTwo;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setStoryProgress(int storyProgress) {
        this.storyProgress = storyProgress;
    }

    public void incrementStoryProgress(){
        this.storyProgress = this.storyProgress + 1;
    }


    public int getRawResourceId(String reward)
    {
        try {
            Class res = R.raw.class;
            Field field = res.getField(reward);
            return field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get raw id.", e);
        }

        return 0;
    }


    public int getColorResourceId(String colour)
    {
        try {
            Class res = R.color.class;
            Field field = res.getField(colour);
            return field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get raw id.", e);
        }

        return 0;
    }

    public int getStringResourceId(String stringName)
    {
        try {
            Class res = R.string.class;
            Field field = res.getField(stringName);
            return field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get raw id.", e);
        }

        return 0;
    }

    public int getStringArrayResourceId(String stringName)
    {
        try {
            Class res = R.array.class;
            Field field = res.getField(stringName);
            return field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get raw id.", e);
        }

        return 0;
    }

}
