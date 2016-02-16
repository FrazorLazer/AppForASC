package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class PopUpVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_video);

        setBackgroundColour();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.55) , (int) (height*.56));


        Bundle extras = getIntent().getExtras();
        String videoName = extras.getString("videoName");
        videoName = videoName.toLowerCase();

        if(videoName.equals("guitar")){
            videoName = "guitar2";
        }


        VideoView popVideo = (VideoView) findViewById(R.id.popVideo);
        int videoID = ((ExtendedApp) getApplication()).getRawResourceId(videoName);
        popVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoID));

        popVideo.requestFocus();

        popVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0f, 0f);
            }
        });

        popVideo.start();
    }

    public void setBackgroundColour(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.popVideoAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.DarkestRed, null);
                layout.setBackgroundColor(colour);

                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.DarkestBlue, null);
                layout.setBackgroundColor(colour);


                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.DarkestGreen, null);
                layout.setBackgroundColor(colour);

                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.DarkestPurple, null);
                layout.setBackgroundColor(colour);


                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.DarkestBlue, null);
                layout.setBackgroundColor(colour);

                break;

        }
    }
}
