package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
                playButton.setBackgroundResource(R.drawable.border_red);
                charButton.setBackgroundResource(R.drawable.border_red);
                settingsButton.setBackgroundResource(R.drawable.border_red);
                tutButton.setBackgroundResource(R.drawable.border_red);
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
                playButton.setBackgroundResource(R.drawable.borders_green);
                charButton.setBackgroundResource(R.drawable.borders_green);
                settingsButton.setBackgroundResource(R.drawable.borders_green);
                tutButton.setBackgroundResource(R.drawable.borders_green);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_purple);
                playButton.setBackgroundResource(R.drawable.borders_purple);
                charButton.setBackgroundResource(R.drawable.borders_purple);
                settingsButton.setBackgroundResource(R.drawable.borders_purple);
                tutButton.setBackgroundResource(R.drawable.borders_purple);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_blue);
                playButton.setBackgroundResource(R.drawable.borders_blue);
                charButton.setBackgroundResource(R.drawable.borders_blue);
                settingsButton.setBackgroundResource(R.drawable.borders_blue);
                tutButton.setBackgroundResource(R.drawable.borders_blue);
                break;

        }
    }

}
