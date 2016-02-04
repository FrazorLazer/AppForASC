package com.example.frazzle.appforasc;


import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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

        TextView player1Deetz = (TextView) findViewById(R.id.player1Deetz);
        TextView player2Deetz = (TextView) findViewById(R.id.player2Deetz);
        TextView storyTitle = (TextView) findViewById(R.id.storyTitle);

        Character character1 = ((ExtendedApp) getApplication()).getCharacterOne();
        Character character2 = ((ExtendedApp) getApplication()).getCharacterTwo();
        String storyName = ((ExtendedApp) getApplication()).getStory();

        Drawable song1 = BitmapDrawable.createFromPath(character1.get_profileImagePath());
        Drawable song2 = BitmapDrawable.createFromPath(character2.get_profileImagePath());
        Drawable placeholder = new ResourcesCompat().getDrawable(getResources(), R.drawable.placeholder_profile_photo, null);


        //Setting Left Player
        if (song1 != null) {
            song1.setBounds(0, 0, 300, 300);
            player1Deetz.setCompoundDrawables(null, song1, null, null);
        }else{
            placeholder.setBounds(0, 0, 300, 300);
            player1Deetz.setCompoundDrawables(null, placeholder, null, null);
        }
        player1Deetz.setText(character1.get_name());


        //Setting Right Player
        if (song2 != null) {
            song2.setBounds(0, 0, 300, 300);
            player2Deetz.setCompoundDrawables(null, song2, null, null);
        }else{
            placeholder.setBounds(0, 0, 300, 300);
            player2Deetz.setCompoundDrawables(null, placeholder, null, null);
        }
        player2Deetz.setText(character2.get_name());



        if (storyName.equals("Park")) {
            storyTitle.setText("At the Park");
        }else if (storyName.equals("Grandparent")){
            storyTitle.setText("With a Grandparent");
        }else if (storyName.equals("Shopping")){
            storyTitle.setText("To the Shops");
        }else{
            storyTitle.setText("The Sleepover");
        }



        setBackgroundColour();

    }


    public void closeWindow(View view){
        onBackPressed();
    }
    public void beginStory(View view){

        //String storyFormat = ((ExtendedApp) getApplication()).getStoryFormat();
        Intent i = new Intent(this, StoryActivity.class);
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
