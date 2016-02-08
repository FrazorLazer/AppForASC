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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CharacterChoiceOne extends AppCompatActivity {

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
        setContentView(R.layout.activity_character_choice_one);

        dbHandler = new CharacterDBHandler(this, null, null, 1);
        titleText = (TextView) findViewById(R.id.titleCC1);
        charGrid = (GridView) findViewById(R.id.charGridView);
        closeButton = (Button) findViewById(R.id.closeButton);
        setBackgroundColour();
        populateAdapterGrid();

        Typeface kristen = Typeface.createFromAsset(getAssets(), "ITCKRIST.TTF");
        titleText.setTypeface(kristen);

    }


    public void populateAdapter(){

        characters = dbHandler.returnCharacters();
        ArrayAdapter arrayAdapter = new characterRowAdapter(this, characters);
        characterListView.setAdapter(arrayAdapter);
        characterListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ExtendedApp) getApplication()).setCharacterOne(characters[position]);
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
                        ((ExtendedApp) getApplication()).setCharacterOne(characters[position]);
                        openCharacterChoice();
                    }
                }
        );

    }

    public void closeWindow(View view){
        onBackPressed();
    }

    private void openCharacterChoice(){
        Intent i = new Intent(this, CharacterChoiceTwo.class);
        startActivity(i);
    }



    public void setBackgroundColour(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.CharacterChoiceOneAct);
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
