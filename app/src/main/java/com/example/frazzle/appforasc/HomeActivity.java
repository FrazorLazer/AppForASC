package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((ExtendedApp) this.getApplication()).setStory("Sandwich");

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


    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundColour();
    }


    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.homeAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);
        int colour = new ResourcesCompat().getColor(getResources(), colourID, null);
        layout.setBackgroundColor(colour);

    }

}
