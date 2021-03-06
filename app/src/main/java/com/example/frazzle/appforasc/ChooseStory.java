package com.example.frazzle.appforasc;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChooseStory extends AppCompatActivity {

    RelativeLayout layout;
    TextView titleText;
    ListView storyList;
    Button closeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_story);
        closeButton = (Button) findViewById(R.id.closeButton);

        assignViews();

        setUpListView();
        setBackgroundColour();

        Typeface kristen = Typeface.createFromAsset(getAssets(), "ITCKRIST.TTF");
        titleText.setTypeface(kristen);


    }

    public void setUpListView(){
        storyList = (ListView) findViewById(R.id.storyList);
        //ArrayAdapter storyAdapter = ArrayAdapter.createFromResource(this, R.array.storyNames, android.R.layout.simple_list_item_1);
        String [] theStories = getResources().getStringArray(R.array.storyNames);
        ArrayAdapter aa = new storyListAdapter(this, theStories);
        storyList.setAdapter(aa);

        storyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String storyName = parent.getItemAtPosition(position).toString();

                switch (position) {

                    case 0:
                        ((ExtendedApp) getApplication()).setStory("Park");
                        ((ExtendedApp) getApplication()).setStoryFormat("Options");
                        break;
                    case 1:
                        ((ExtendedApp) getApplication()).setStory("Sleepover");
                        ((ExtendedApp) getApplication()).setStoryFormat("Options");
                        break;
                    case 2:
                        ((ExtendedApp) getApplication()).setStory("Shopping");
                        ((ExtendedApp) getApplication()).setStoryFormat("NoOptions");
                        break;
                    default:
                        ((ExtendedApp) getApplication()).setStory("Sleepover");
                        ((ExtendedApp) getApplication()).setStoryFormat("Options");
                        break;
                }

                ((ExtendedApp) getApplication()).setInTutorial(false);
                ((ExtendedApp) getApplication()).setStoryProgress(1);
                openCharacterChoice();
            }
        });

    }

    public void closeWindow(View view){
        onBackPressed();
    }

    private void openCharacterChoice(){
        Intent i = new Intent(this, CharacterChoiceOne.class);
        startActivity(i);
    }

    private void assignViews(){

        titleText = (TextView) findViewById(R.id.titleText);
        storyList = (ListView) findViewById(R.id.storyList);

}

    public void setBackgroundColour(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.chooseStoryAct);
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
