package com.example.frazzle.appforasc;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ChooseStory extends AppCompatActivity {

    RelativeLayout layout;
    ListView storyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_story);

        setUpListView();
        setBackgroundColour();

    }

    public void setUpListView(){
        storyList = (ListView) findViewById(R.id.storyList);
        ArrayAdapter storyAdapter = ArrayAdapter.createFromResource(this, R.array.storyNames, android.R.layout.simple_list_item_1);
        storyList.setAdapter(storyAdapter);

        storyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String storyName = parent.getItemAtPosition(position).toString();
                ((ExtendedApp) getApplication()).setStory(storyName);
                openCharacterChoice();
            }
        });

    }

    private void openCharacterChoice(){
        Intent i = new Intent(this, CharacterChoiceOne.class);
        startActivity(i);
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
                //Toast.makeText(this, "RedSet", Toast.LENGTH_LONG).show();
                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                //Toast.makeText(this, "BlueSet", Toast.LENGTH_LONG).show();
                break;

            case ("White"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundWhite, null);
                layout.setBackgroundColor(colour);
                //Toast.makeText(this, "RedSet", Toast.LENGTH_LONG).show();
                break;

            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                break;

        }
    }
}
