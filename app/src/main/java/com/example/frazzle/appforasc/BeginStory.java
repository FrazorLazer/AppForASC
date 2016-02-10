package com.example.frazzle.appforasc;


import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


import adapters.MyFragmentPagerAdapter;
import fragments.Fragment1;
import fragments.Fragment2;
import fragments.Fragment3;

public class BeginStory extends AppCompatActivity {

    TextView player1Deetz;
    TextView player2Deetz;
    TextView storyTitle;
    Button closeButton;
    Button begin;
    TextView p1;
    TextView p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_story);

        player1Deetz = (TextView) findViewById(R.id.player1Deetz);
        begin = (Button) findViewById(R.id.button6);
        player2Deetz = (TextView) findViewById(R.id.player2Deetz);
        storyTitle = (TextView) findViewById(R.id.storyTitle);
        closeButton = (Button) findViewById(R.id.closeButton);
        p1 = (TextView) findViewById(R.id.player1Text);
        p2 = (TextView) findViewById(R.id.player2Text);

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

        Typeface kristen = Typeface.createFromAsset(getAssets(), "ITCKRIST.TTF");
        storyTitle.setTypeface(kristen);
        setTextSize();

    }


    public void closeWindow(View view){
        onBackPressed();
    }
    public void beginStory(View view){

        //String storyFormat = ((ExtendedApp) getApplication()).getStoryFormat();
        Intent i = new Intent(this, StoryActivity.class);
        startActivity(i);
    }

    public void setBackgroundColour(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.BeginStoryAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                layout.setBackgroundColor(colour);
                storyTitle.setBackgroundResource(R.drawable.border_red);
                player1Deetz.setBackgroundResource(R.drawable.border_red);
                player2Deetz.setBackgroundResource(R.drawable.border_red);
                player1Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestRed, null));
                player2Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestRed, null));
                closeButton.setBackgroundResource(R.drawable.roundbuttonr);
                begin.setBackgroundResource(R.drawable.roundbuttonr);


                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                storyTitle.setBackgroundResource(R.drawable.borders_blue);
                player1Deetz.setBackgroundResource(R.drawable.borders_blue);
                player2Deetz.setBackgroundResource(R.drawable.borders_blue);
                player1Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestBlue, null));
                player2Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestBlue, null));
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                begin.setBackgroundResource(R.drawable.roundbutton);
                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                layout.setBackgroundColor(colour);
                storyTitle.setBackgroundResource(R.drawable.borders_green);
                player1Deetz.setBackgroundResource(R.drawable.borders_green);
                player2Deetz.setBackgroundResource(R.drawable.borders_green);
                player1Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestGreen, null));
                player2Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestGreen, null));
                closeButton.setBackgroundResource(R.drawable.roundbuttong);
                begin.setBackgroundResource(R.drawable.roundbuttong);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                layout.setBackgroundColor(colour);
                storyTitle.setBackgroundResource(R.drawable.borders_purple);
                player1Deetz.setBackgroundResource(R.drawable.borders_purple);
                player2Deetz.setBackgroundResource(R.drawable.borders_purple);
                player1Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestPurple, null));
                player2Deetz.setTextColor(new ResourcesCompat().getColor(getResources(), R.color.LightestPurple, null));
                closeButton.setBackgroundResource(R.drawable.roundbuttonp);
                begin.setBackgroundResource(R.drawable.roundbuttonp);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                begin.setBackgroundResource(R.drawable.roundbutton);
                break;

        }
    }

    public void setTextSize(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                storyTitle.setTextSize(40);
                begin.setTextSize(20);
                p1.setTextSize(25);
                p2.setTextSize(25);
                break;

            case ("Medium"):

                storyTitle.setTextSize(50);
                begin.setTextSize(25);
                p1.setTextSize(30);
                p2.setTextSize(30);
                break;

            case ("Large"):
                storyTitle.setTextSize(60);
                begin.setTextSize(30);
                p1.setTextSize(35);
                p2.setTextSize(35);
                break;


            default:
                storyTitle.setTextSize(50);
                begin.setTextSize(25);
                p1.setTextSize(30);
                p2.setTextSize(30);
                break;
        }
    }
}
