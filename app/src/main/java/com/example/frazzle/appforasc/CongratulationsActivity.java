package com.example.frazzle.appforasc;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CongratulationsActivity extends AppCompatActivity {

    String reward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        Bundle args = getIntent().getExtras();

        int whichChar = args.getInt("seq");

        if (whichChar == 1) {
            reward = ((ExtendedApp) getApplication()).getCharacterOne().get_reward();
        }else{
            reward = ((ExtendedApp) getApplication()).getCharacterTwo().get_reward();
        }

        playAudio();
    }


    public void continueStory(View view){
        this.finish();
    }


    public void playAudio(){

        if (reward.equals("No Sound")){
            return;
        }

        String lowercaseReward = reward.toLowerCase();
        int x = ((ExtendedApp) this.getApplication()).getRawResourceId(lowercaseReward);
        MediaPlayer player;
        player = MediaPlayer.create(this, x);
        player.start();

    }
}
