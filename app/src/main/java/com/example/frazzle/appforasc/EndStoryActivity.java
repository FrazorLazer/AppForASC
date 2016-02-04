package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EndStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_story);


        Bundle extras = getIntent().getExtras();
        String lastLineText = extras.getString("lastLine");

        TextView lastLine = (TextView) findViewById(R.id.lastLine);
        lastLine.setText(lastLineText);

        setBackgroundColour();

    }


    public void endStory(View view){

        ((ExtendedApp) getApplication()).setStoryProgress(1);
        Intent i = new Intent(this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = "Background" + sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.endAct);
        int colourID = ((ExtendedApp) getApplication()).getColorResourceId(backgroundColour);

        if (colourID == 0x0){
            layout.setBackgroundColor(Integer.parseInt("142a78", 16));
            return;
        }

        int colour = new ResourcesCompat().getColor(getResources(), colourID, null);
        layout.setBackgroundColor(colour);

    }
}
