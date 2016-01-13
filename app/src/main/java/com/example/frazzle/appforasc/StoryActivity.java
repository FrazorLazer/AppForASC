package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    TextView player1Deetz;
    TextView player2Deetz;
    TextView storyTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        player1Deetz = (TextView) findViewById(R.id.player1Deetz);
        player2Deetz = (TextView) findViewById(R.id.player2Deetz);
        storyTitle = (TextView) findViewById(R.id.storyTitle);

        Character character1 = ((ExtendedApp) getApplication()).getCharacterOne();
        Character character2 = ((ExtendedApp) getApplication()).getCharacterTwo();
        String storyName = ((ExtendedApp) getApplication()).getStory();

        player1Deetz.setText("Player1\n\nName: " + character1.get_name() + "\nReward: " + character1.get_reward());
        player2Deetz.setText("Player2\n\nName: " + character2.get_name() + "\nReward: " + character2.get_reward());
        storyTitle.setText(storyName);

        setBackgroundColour();

    }

    public void setBackgroundColour() {
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.storyAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);
        int colour = new ResourcesCompat().getColor(getResources(), colourID, null);
        layout.setBackgroundColor(colour);
    }
}
