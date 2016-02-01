package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class CharacterChoiceTwo extends AppCompatActivity {

    RelativeLayout layout;
    CharacterDBHandler dbHandler;
    ListView characterListView;
    Character[] characters;
    GridView charGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_choice_two);


        dbHandler = new CharacterDBHandler(this, null, null, 1);
        //characterListView = (ListView) findViewById(R.id.characterList2);
        charGrid = (GridView) findViewById(R.id.charGridView);
        setBackgroundColour();
        populateAdapterGrid();

    }


    public void populateAdapter(){

        characters = dbHandler.returnCharacters();
        ArrayAdapter arrayAdapter = new characterRowAdapter(this, characters);
        characterListView.setAdapter(arrayAdapter);
        characterListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ExtendedApp) getApplication()).setCharacterTwo(characters[position]);
                        openCharacterChoice();
                    }
                }
        );

    }
    public void populateAdapterGrid(){

        characters = dbHandler.returnCharacters();
        ArrayAdapter arrayAdapter = new characterGridAdapter(this, characters);
        charGrid.setAdapter(arrayAdapter);
        charGrid.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ExtendedApp) getApplication()).setCharacterTwo(characters[position]);
                        openCharacterChoice();
                    }
                }
        );

    }


    private void openCharacterChoice(){
        Intent i = new Intent(this, BeginStory.class);
        startActivity(i);
    }





    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.CharacterChoiceTwoAct);
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



