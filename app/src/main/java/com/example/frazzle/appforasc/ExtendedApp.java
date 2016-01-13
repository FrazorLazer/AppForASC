package com.example.frazzle.appforasc;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Frazzle on 13/01/2016.
 */
public class ExtendedApp extends Application{

    private Character characterOne;
    private Character characterTwo;
    private String story;
    private int storyProgress = 0;

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
}
