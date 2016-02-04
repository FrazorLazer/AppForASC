package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CongratulationsActivity extends AppCompatActivity {

    String reward;
    Button continueButton;
    TextView otherIdeas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        ImageView thumbsUp = (ImageView) findViewById(R.id.thumbImage);
        otherIdeas = (TextView) findViewById(R.id.listOfIdeas);
        continueButton = (Button) findViewById(R.id.continueButton);

        Bundle args = getIntent().getExtras();
        String [] options = args.getStringArray("options");


        if (((ExtendedApp) getApplication()).getStoryFormat().equals("Options")){
            String theText = "Here are all the possible options:\n\n";
            for(int i = 0; i<3; i++){
                theText += "- ";
                theText += options[i];
                theText += "\n";
            }
            otherIdeas.setText(theText);
        }else{
            otherIdeas.setVisibility(View.GONE);
            ViewGroup.LayoutParams vg_lp = thumbsUp.getLayoutParams();
            RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
            rl_lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            thumbsUp.setLayoutParams(rl_lp);
        }

        int whichChar = args.getInt("seq");
        if (whichChar == 1) {
            reward = ((ExtendedApp) getApplication()).getCharacterOne().get_reward();
        }else{
            reward = ((ExtendedApp) getApplication()).getCharacterTwo().get_reward();
        }

        playAudio();


        setBackgroundColour();

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


    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.congratsAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                layout.setBackgroundColor(colour);
                continueButton.setBackgroundResource(R.drawable.roundbuttonr);
                otherIdeas.setBackgroundResource(R.drawable.border_red);

                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                continueButton.setBackgroundResource(R.drawable.roundbutton);
                otherIdeas.setBackgroundResource(R.drawable.borders_blue);
                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                layout.setBackgroundColor(colour);
                continueButton.setBackgroundResource(R.drawable.roundbuttong);
                otherIdeas.setBackgroundResource(R.drawable.borders_green);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                layout.setBackgroundColor(colour);
                continueButton.setBackgroundResource(R.drawable.roundbuttonp);
                otherIdeas.setBackgroundResource(R.drawable.borders_purple);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                continueButton.setBackgroundResource(R.drawable.roundbutton);
                otherIdeas.setBackgroundResource(R.drawable.borders_blue);
                break;

        }

    }
}
