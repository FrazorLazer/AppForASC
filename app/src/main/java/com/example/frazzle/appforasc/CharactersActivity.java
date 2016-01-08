package com.example.frazzle.appforasc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CharactersActivity extends AppCompatActivity {

    RelativeLayout layout;
    EditText charName;
    EditText charReward;
    TextView databaseText;
    CharacterDBHandler dbHandler;
    ListView characterList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        charName = (EditText) findViewById(R.id.charName);
        charReward = (EditText) findViewById(R.id.charReward);
        //databaseText = (TextView) findViewById(R.id.databaseText);
        dbHandler = new CharacterDBHandler(this, null, null, 1);

        Character [] characters = dbHandler.returnCharacters();

        characterList = (ListView) findViewById(R.id.characterList);
        //String settingsOptions[] = new String[] {"Background Colour", "Font Size", "Vibration"};
        ListAdapter arrayAdapter = new characterRowAdapter(this, characters);
        characterList.setAdapter(arrayAdapter);
        /*
        settingsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        settingsView.showContextMenuForChild(view);
                    }
                }
        );

        */

        //printDB();
    }

    public void printDB(){
        String dbString = dbHandler.dbToString();
        databaseText.setText(dbString);
        charName.setText("");
        charReward.setText("");
    }

    public void addAndUpdate(View view){

        String name = charName.getText().toString();
        String reward = charReward.getText().toString();
        Character character = new Character(name, reward);

        dbHandler.addCharacter(character);
       // printDB();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundColour();
    }


    public void setBackgroundColour(){
        SharedPreferences sharedPref = getSharedPreferences("colourInfo", Context.MODE_PRIVATE);
        String backgroundColour = sharedPref.getString("backgroundC", "");
        layout = (RelativeLayout) findViewById(R.id.charactersAct);
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
