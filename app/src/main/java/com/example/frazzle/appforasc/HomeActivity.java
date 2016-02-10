package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout layout;
    TextView titleText;
    Button playButton;
    Button charButton;
    Button settingsButton;
    Button tutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        titleText = (TextView) findViewById(R.id.titleText);
        playButton = (Button) findViewById(R.id.button);
        charButton = (Button) findViewById(R.id.button2);
        settingsButton = (Button) findViewById(R.id.button3);
        tutButton = (Button) findViewById(R.id.button4);


        Typeface kristen = Typeface.createFromAsset(getAssets(), "ITCKRIST.TTF");
        titleText.setTypeface(kristen);


    }


    public void clickPlay(View view){
        Intent i = new Intent(this, ChooseStory.class);
        startActivity(i);
    }


    public void clickCharacters(View view){
        Intent i = new Intent(this, CharactersActivity.class);
        startActivity(i);
    }


    public void clickSettings(View view){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }



    public void clickTutorial(View view){
        Intent i = new Intent(this, TutorialActivity.class);
        startActivity(i);
    }

    public void openSwipeTest(View view){
        Intent i = new Intent(this, BeginStory.class);
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundColour();
        setTextSize();
    }


    public void setBackgroundColour(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.homeAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.border_red);
                playButton.setBackgroundResource(R.drawable.roundbuttonr);
                charButton.setBackgroundResource(R.drawable.roundbuttonr);
                settingsButton.setBackgroundResource(R.drawable.roundbuttonr);
                tutButton.setBackgroundResource(R.drawable.roundbuttonr);
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_blue);
                playButton.setBackgroundResource(R.drawable.roundbutton);
                charButton.setBackgroundResource(R.drawable.roundbutton);
                settingsButton.setBackgroundResource(R.drawable.roundbutton);
                tutButton.setBackgroundResource(R.drawable.roundbutton);

                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_green);
                playButton.setBackgroundResource(R.drawable.roundbuttong);
                charButton.setBackgroundResource(R.drawable.roundbuttong);
                settingsButton.setBackgroundResource(R.drawable.roundbuttong);
                tutButton.setBackgroundResource(R.drawable.roundbuttong);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_purple);
                playButton.setBackgroundResource(R.drawable.roundbuttonp);
                charButton.setBackgroundResource(R.drawable.roundbuttonp);
                settingsButton.setBackgroundResource(R.drawable.roundbuttonp);
                tutButton.setBackgroundResource(R.drawable.roundbuttonp);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_blue);
                playButton.setBackgroundResource(R.drawable.roundbutton);
                charButton.setBackgroundResource(R.drawable.roundbutton);
                settingsButton.setBackgroundResource(R.drawable.roundbutton);
                tutButton.setBackgroundResource(R.drawable.roundbutton);
                break;

        }
    }



    public void setTextSize(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                titleText.setTextSize(40);
                playButton.setTextSize(20);
                charButton.setTextSize(20);
                settingsButton.setTextSize(20);
                tutButton.setTextSize(20);
                break;

            case ("Medium"):

                titleText.setTextSize(50);
                playButton.setTextSize(28);
                charButton.setTextSize(28);
                settingsButton.setTextSize(28);
                tutButton.setTextSize(28);
                break;

            case ("Large"):
                titleText.setTextSize(60);
                playButton.setTextSize(34);
                charButton.setTextSize(34);
                settingsButton.setTextSize(34);
                tutButton.setTextSize(34);
                break;


            default:
                titleText.setTextSize(50);
                playButton.setTextSize(28);
                charButton.setTextSize(28);
                settingsButton.setTextSize(28);
                tutButton.setTextSize(28);
                break;
        }
    }
}
