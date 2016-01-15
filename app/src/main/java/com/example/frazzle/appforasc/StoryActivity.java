package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.MyFragmentPagerAdapter;
import fragments.Fragment1;
import fragments.Fragment2;
import fragments.Fragment3;

public class StoryActivity extends AppCompatActivity implements Fragment1.Fragment1Listener {

    TextView player1Deetz;
    TextView player2Deetz;
    TextView storyTitle;
    MyFragmentPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;


    @Override
    public void moveLeft() {
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void moveRight() {
        mViewPager.setCurrentItem(2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment3());

        mDemoCollectionPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.setCurrentItem(1);
        /*
        player1Deetz = (TextView) findViewById(R.id.player1Deetz);
        player2Deetz = (TextView) findViewById(R.id.player2Deetz);
        storyTitle = (TextView) findViewById(R.id.storyTitle);

        Character character1 = ((ExtendedApp) getApplication()).getCharacterOne();
        Character character2 = ((ExtendedApp) getApplication()).getCharacterTwo();

        player1Deetz.setText(findTheString("Act", character1.get_name(), character2.get_name()));
        player2Deetz.setText(findTheString("Guess", character1.get_name(), character2.get_name()));
        storyTitle.setText(findTheString("Story", character1.get_name(), character2.get_name()));
        */


        //setBackgroundColour();

    }


    public String findTheString(String context, String transferred1, String transferred2){

        String keyword = "";
        keyword += ((ExtendedApp) getApplication()).getStory();
        keyword += context;
        keyword += Integer.toString(((ExtendedApp) getApplication()).getStoryProgress());

        int id = (((ExtendedApp) getApplication()).getStringResourceId(keyword));
        String raw = getResources().getString(id);
        String replace = raw.replaceAll("James", transferred1);
        replace = replace.replaceAll("Sarah", transferred2);
        return replace;

    }

    public void progressStory(View view){

        String storyName = ((ExtendedApp) getApplication()).getStory();
        int progress = ((ExtendedApp) getApplication()).getStoryProgress();

        if(storyName.equals("Park") && progress > 7){
            ((ExtendedApp) getApplication()).setStoryProgress(1);
            Intent i = new Intent(this, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return;

        }

        if(storyName.equals("Grandparent") && progress > 4){
            ((ExtendedApp) getApplication()).setStoryProgress(1);
            Intent i = new Intent(this, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        ((ExtendedApp) getApplication()).incrementStoryProgress();
        Intent i = new Intent(this, StoryActivity.class);
        startActivity(i);
        this.finish();

    }

/*
    public void setBackgroundColour() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.storyAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);
        int colour = new ResourcesCompat().getColor(getResources(), colourID, null);
        layout.setBackgroundColor(colour);
    }
    */
}
