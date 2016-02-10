package com.example.frazzle.appforasc;

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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CharacterChoiceTwo extends AppCompatActivity {

    RelativeLayout layout;
    CharacterDBHandler dbHandler;
    ListView characterListView;
    Character[] characters;
    GridView charGrid;
    TextView titleText;
    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_choice_two);


        dbHandler = new CharacterDBHandler(this, null, null, 1);
        titleText = (TextView) findViewById(R.id.titleCC2);
        charGrid = (GridView) findViewById(R.id.charGridView);
        closeButton = (Button) findViewById(R.id.closeButton);
        setBackgroundColour();
        populateAdapterGrid();

        Typeface kristen = Typeface.createFromAsset(getAssets(), "ITCKRIST.TTF");
        titleText.setTypeface(kristen);

        setTextSize();
    }


    public void closeWindow(View view){
        onBackPressed();
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


    public void setTextSize(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                titleText.setTextSize(35);
                break;

            case ("Medium"):

                titleText.setTextSize(40);
                break;

            case ("Large"):
                titleText.setTextSize(50);
                break;


            default:
                titleText.setTextSize(40);
                break;
        }
    }
}



