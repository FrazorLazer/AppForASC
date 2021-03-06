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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class CharactersActivity extends AppCompatActivity {

    RelativeLayout layout;
    CharacterDBHandler dbHandler;
    ListView characterListView;
    ArrayAdapter arrayAdapter;
    Character [] characters;
    Button newCharButton;
    Button closeButton;
    GridView grid;
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        newCharButton = (Button) findViewById(R.id.newChar);
        closeButton = (Button) findViewById(R.id.closeButton);
        title = (TextView) findViewById(R.id.title);
        dbHandler = new CharacterDBHandler(this, null, null, 1);
        //characterListView = (ListView) findViewById(R.id.characterList);
        grid = (GridView) findViewById(R.id.charGridView);
        setBackgroundColour();

        String s = ((ExtendedApp) this.getApplication()).getStory();

        Typeface kristen = Typeface.createFromAsset(getAssets(), "ITCKRIST.TTF");
        title.setTypeface(kristen);

        setTextSize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateAdapterGrid();
    }

    public void closeWindow(View view){
        onBackPressed();
    }

    public void populateAdapter(){

        characters = dbHandler.returnCharacters();
        arrayAdapter = new characterRowAdapter(this, characters);
        characterListView.setAdapter(arrayAdapter);
        characterListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openCharacterView(characters[position]);
                    }
                }
        );

    }

    public void populateAdapterGrid(){

        characters = dbHandler.returnCharacters();
        arrayAdapter = new characterGridAdapter(this, characters);
        grid.setAdapter(arrayAdapter);
        grid.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openCharacterView(characters[position]);
                    }
                }
        );

    }

    public void openCharacterView(Character c){
        Intent i = new Intent(CharactersActivity.this, CharacterCreate.class);

        i.putExtra("name", c.get_name());
        i.putExtra("reward", c.get_reward());
        i.putExtra("id", c.get_id());
        i.putExtra("pathname", c.get_profileImagePath());
        i.putExtra("gender", c.get_gender());
        startActivity(i);
    }


    public void createCharacter(View view){
        Intent i = new Intent(CharactersActivity.this, CharacterCreate.class);
        startActivity(i);
    }


    public void setBackgroundColour(){

        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.charactersAct);
        int colour;

        switch(backgroundColour) {
            case ("Red"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundRed, null);
                layout.setBackgroundColor(colour);
                newCharButton.setBackgroundResource(R.drawable.roundbuttonr);
                closeButton.setBackgroundResource(R.drawable.roundbuttonr);
                title.setBackgroundResource(R.drawable.border_red);

                break;

            case ("Blue"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                newCharButton.setBackgroundResource(R.drawable.roundbutton);
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                title.setBackgroundResource(R.drawable.borders_blue);

                break;

            case ("Green"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundGreen, null);
                layout.setBackgroundColor(colour);
                newCharButton.setBackgroundResource(R.drawable.roundbuttong);
                closeButton.setBackgroundResource(R.drawable.roundbuttong);
                title.setBackgroundResource(R.drawable.borders_green);

                break;

            case ("Purple"):
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundPurple, null);
                layout.setBackgroundColor(colour);
                newCharButton.setBackgroundResource(R.drawable.roundbuttonp);
                closeButton.setBackgroundResource(R.drawable.roundbuttonp);
                title.setBackgroundResource(R.drawable.borders_purple);


                break;


            default:
                colour = new ResourcesCompat().getColor(getResources(), R.color.BackgroundBlue, null);
                layout.setBackgroundColor(colour);
                newCharButton.setBackgroundResource(R.drawable.roundbutton);
                closeButton.setBackgroundResource(R.drawable.roundbutton);
                title.setBackgroundResource(R.drawable.borders_blue);
                break;

        }
    }


    public void setTextSize(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String textSize = sharedPref.getString("textSize", "");

        switch(textSize){
            case ("Small"):
                title.setTextSize(40);
                newCharButton.setTextSize(15);
                break;

            case ("Medium"):

                title.setTextSize(50);
                newCharButton.setTextSize(20);
                break;

            case ("Large"):
                title.setTextSize(60);
                newCharButton.setTextSize(25);
                break;


            default:
                title.setTextSize(50);
                newCharButton.setTextSize(30);
                break;
        }
    }

}
