package com.example.frazzle.appforasc;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import adapters.MyFragmentPagerAdapter;
import fragments.Fragment1;
import fragments.Fragment2;
import fragments.Fragment3;

public class BeginStory extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_story);
/*
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment3());

        mDemoCollectionPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.setCurrentItem(1);

 */
        TextView player1Deetz = (TextView) findViewById(R.id.player1Deetz);
        TextView player2Deetz = (TextView) findViewById(R.id.player2Deetz);
        TextView storyTitle = (TextView) findViewById(R.id.storyTitle);

        String character1Name = ((ExtendedApp) getApplication()).getCharacterOne().get_name();
        String character2Name = ((ExtendedApp) getApplication()).getCharacterTwo().get_name();
        String storyName = ((ExtendedApp) getApplication()).getStory();


        player1Deetz.setText("Player 1\n\n" + character1Name);
        player2Deetz.setText("Player 2\n\n" + character2Name);

        if (storyName.equals("Park")) {
            storyTitle.setText("At the Park");
        }else{
            storyTitle.setText("With a Grandparent");
        }

        setBackgroundColour();

    }



    public void beginStory(View view){

        String storyFormat = ((ExtendedApp) getApplication()).getStoryFormat();
        Intent i;
        if (storyFormat.equals("Options")) {
            i = new Intent(this, StoryActivity.class);
        }else{
            i = new Intent(this, StoryActivityAlt.class);
        }

        startActivity(i);
    }

    public void setBackgroundColour() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.BeginStoryAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);
        int colour = new ResourcesCompat().getColor(getResources(), colourID, null);
        layout.setBackgroundColor(colour);
    }
}
