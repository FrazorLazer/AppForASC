package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EndStoryActivity extends AppCompatActivity {


    Button homeButton;
    TextView congrats;
    TextView lastLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_story);

        homeButton = (Button) findViewById(R.id.homeButton);
        congrats = (TextView) findViewById(R.id.congrats);

        Bundle extras = getIntent().getExtras();
        String lastLineText = extras.getString("lastLine");

        lastLine = (TextView) findViewById(R.id.lastLine);
        lastLine.setText(lastLineText);

        setBackgroundColour();
        setTextSize();

    }


    public void endStory(View view){

        ((ExtendedApp) getApplication()).setStoryProgress(1);
        Intent i = new Intent(this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.endAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                layout.setBackgroundColor(colour);
                homeButton.setBackgroundResource(R.drawable.roundbuttonr);

                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                homeButton.setBackgroundResource(R.drawable.roundbutton);
                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                layout.setBackgroundColor(colour);
                homeButton.setBackgroundResource(R.drawable.roundbuttong);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                layout.setBackgroundColor(colour);
                homeButton.setBackgroundResource(R.drawable.roundbuttonp);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                homeButton.setBackgroundResource(R.drawable.roundbutton);
                break;

        }
    }


    public void setTextSize(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                congrats.setTextSize(40);
                lastLine.setTextSize(15);
                homeButton.setTextSize(25);
                break;

            case ("Medium"):

                congrats.setTextSize(50);
                lastLine.setTextSize(20);
                homeButton.setTextSize(30);
                break;

            case ("Large"):
                congrats.setTextSize(60);
                lastLine.setTextSize(25);
                homeButton.setTextSize(35);
                break;


            default:
                congrats.setTextSize(50);
                lastLine.setTextSize(20);
                homeButton.setTextSize(30);
                break;
        }
    }
}
