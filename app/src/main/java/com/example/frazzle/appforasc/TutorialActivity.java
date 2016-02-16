package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity {

    RelativeLayout layout;
    ListView tutorialList;
    Button closeButton;
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        closeButton = (Button) findViewById(R.id.closeButton);
        titleText = (TextView) findViewById(R.id.titleText);

        setUpListView();

    }


    public void setUpListView(){
        tutorialList = (ListView) findViewById(R.id.tutorialList);
        //ArrayAdapter storyAdapter = ArrayAdapter.createFromResource(this, R.array.storyNames, android.R.layout.simple_list_item_1);
        String [] theStories = getResources().getStringArray(R.array.tutorialNames);
        ArrayAdapter aa = new storyListAdapter(this, theStories);
        tutorialList.setAdapter(aa);

        tutorialList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String storyName = parent.getItemAtPosition(position).toString();

                switch (position) {

                    case 0:
                        startCharacterTut();
                        break;
                    case 1:
                        startOptionsTut();
                        break;
                    case 2:
                        startNoOptionsTut();
                        break;
                    default:
                        ((ExtendedApp) getApplication()).setStory("Sleepover");
                        ((ExtendedApp) getApplication()).setStoryFormat("Options");
                        break;
                }

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundColour();
    }

    public void startCharacterTut(){
        Intent i = new Intent(this, TutorialSwipeActivity.class);
        i.putExtra("type", "char");
        startActivity(i);
    }


    public void startOptionsTut(){
        Intent i = new Intent(this, TutorialSwipeActivity.class);
        i.putExtra("type", "options");
        startActivity(i);
    }


    public void startNoOptionsTut(){
        Intent i = new Intent(this, TutorialSwipeActivity.class);
        i.putExtra("type", "noOptions");
        startActivity(i);
    }


    public void closeWindow(View view){
        onBackPressed();
    }

    public void setBackgroundColour(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.tutorialAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.border_red);
                closeButton.setBackgroundResource(R.drawable.roundbuttonr);

                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_blue);
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_green);
                closeButton.setBackgroundResource(R.drawable.roundbuttong);
                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                layout.setBackgroundColor(colour);
                titleText.setBackgroundResource(R.drawable.borders_purple);
                closeButton.setBackgroundResource(R.drawable.roundbuttonp);

                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                break;

        }
    }
}
